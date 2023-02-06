package broker

import java.util.Collections

import akka.actor.ActorSystem
import cats.data.Validated
import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.IOApp
import co.topl.daml.DamlAppContext
import co.topl.daml.ToplContext
import com.daml.ledger.javaapi.data.FiltersByParty
import com.daml.ledger.javaapi.data.LedgerOffset
import com.daml.ledger.javaapi.data.NoFilter
import com.daml.ledger.rxjava.DamlLedgerClient
import fs2.interop.reactivestreams._
import io.circe.generic.auto._
import io.grpc.netty.GrpcSslContexts
import org.http4s.BasicCredentials
import org.http4s.Headers
import org.http4s.Method
import org.http4s.Request
import org.http4s.Uri
import org.http4s.circe._
import org.http4s.headers.Authorization
import scopt.OParser
import org.http4s.ember.client.EmberClientBuilder

object BrokerMain
    extends IOApp
    with PolyProcessorRegistrationModule
    with AssetTransferProcessorRegistrationModule
    with AssetMintingProcessorRegistrationModule
    with BalanceProcessorModule
    with ParameterProcessorModule {

  case class LoginResponse(access_token: String, token: String)
  implicit val loginResponseDecoder = jsonOf[IO, LoginResponse]

  def login2DamlHub(
      damlHost: String,
      damlAccessToken: String
  ): IO[Option[String]] = for {
    uri <- IO.fromEither(
      Uri.fromString(s"https://${damlHost}/.hub/v1/sa/login")
    )
    req = Request[IO](
      method = Method.POST,
      uri = uri
    ).withHeaders(
      Headers(
        Authorization(BasicCredentials(damlAccessToken))
      )
    )
    res <- EmberClientBuilder
      .default[IO]
      .build
      .use(c => c.run(req).use(r => r.as[LoginResponse]))
  } yield Some(res.access_token)

  def runWithParams(paramConfig: CLIParamConfigValidatedInput) = {
    (for {
      someAccessToken <- if (paramConfig.damlHub) paramConfig.damlAccessToken
        .map(t => login2DamlHub(paramConfig.damlHost, t))
        .getOrElse(IO.pure(None)) else IO.pure(paramConfig.damlAccessToken)
      client <- createClient(
        paramConfig.damlHost,
        paramConfig.damlPort,
        paramConfig.damlSecurityEnabled,
        someAccessToken
      )
      _ <- connect(client)
      operatorParty <- IO(paramConfig.damlOperatorParty)
      damlAppContext <- IO(
        new DamlAppContext(paramConfig.damlApplicationId, operatorParty, client)
      )
      toplContext <- IO(
        new ToplContext(
          ActorSystem(),
          paramConfig.provider
        )
      )
      transactions <- getTransactions(client, operatorParty)
    } yield {
      implicit val damlAppContextImpl = damlAppContext
      implicit val toplContextImpl = toplContext
      implicit val transactionsImpl = transactions

      for {
        _ <- registerTransferProcessor()
        _ <- registerSignedTransferProcessor()
        _ <- paramConfig.keyfileAndPassword
          .map(e => registerUnsignedTransferProcessor(e._1.getPath(), e._2))
          .getOrElse(IO.unit)
        _ <- paramConfig.keyfileAndPassword
          .map(e => registerUnsignedMintingProcessor(e._1.getPath(), e._2))
          .getOrElse(IO.unit)
        _ <- registerMintingRequestProcessor()
        _ <- registerAssetTransferRequestProcessor()
        _ <- registerSignedMintingRequestProcessor()
        _ <- registerSignedAssetTransferRequestProcessor()
        _ <- registerAssetBalanceProcessor()
        _ <- fromPublisher(transactionsImpl, 1)(IO.asyncForIO).compile.drain
        // _ <- IO.never[Unit]
      } yield ExitCode.Success
    }).flatten
  }

  override def run(args: List[String]): IO[ExitCode] = {
    OParser.parse(parser, args, CLIParamConfigInput()) match {
      case Some(paramConfig) =>
        validateParams(paramConfig) match {
          case Validated.Valid(validatedInput) =>
            runWithParams(validatedInput)
          case Validated.Invalid(errors) =>
            IO.println(
              "The broker was launched with invalid parameters:\n" + errors.toList
                .map(s => " - " + s)
                .mkString("\n")
            ) *>
              IO.pure(ExitCode.Error)
        }
      case None =>
        IO.pure(ExitCode.Error)
    }

  }

  def getTransactions(client: DamlLedgerClient, operatorParty: String) = IO(
    client
      .getTransactionsClient()
      .getTransactions(
        LedgerOffset.LedgerEnd.getInstance(),
        new FiltersByParty(
          Collections.singletonMap(operatorParty, NoFilter.instance)
        ),
        true
      )
  )

  def createClient(
      host: String,
      port: Int,
      damlSecurityEnabled: Boolean,
      damlAccessToken: Option[String]
  ) = IO {

    val enableSecurity: DamlLedgerClient.Builder => DamlLedgerClient.Builder =
      (if (damlSecurityEnabled)
         _.withSslContext(GrpcSslContexts.forClient().build())
       else identity _)
    val enableAccessToken
        : DamlLedgerClient.Builder => DamlLedgerClient.Builder =
      damlAccessToken
        .map(x => (y: DamlLedgerClient.Builder) => y.withAccessToken(x))
        .getOrElse(identity _)

    enableSecurity
      .compose(enableAccessToken)(DamlLedgerClient.newBuilder(host, port))
      .build()
  }

  def connect(client: DamlLedgerClient) = IO(client.connect())

}
