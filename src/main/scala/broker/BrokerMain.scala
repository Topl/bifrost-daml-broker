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
import com.daml.ledger.javaapi.data.GetUserRequest
import com.daml.ledger.javaapi.data.LedgerOffset
import com.daml.ledger.javaapi.data.NoFilter
import com.daml.ledger.rxjava.DamlLedgerClient
import com.daml.ledger.rxjava.UserManagementClient
import scopt.OParser

object BrokerMain
    extends IOApp
    with PolyProcessorRegistrationModule
    with AssetTransferProcessorRegistrationModule
    with AssetMintingProcessorRegistrationModule
    with BalanceProcessorModule
    with ParameterProcessorModule {

  val OPERATOR_USER = "public";
  val APP_ID = "toplBrokerApp";


  def runWithParams(paramConfig: CLIParamConfigValidatedInput) = {
    (for {
      client <- createClient(paramConfig.damlHost, paramConfig.damlPort)
      _ <- connect(client)
      userManagementClient <- getUserManagementClient(client)
      operatorParty <- getOperatorParty(userManagementClient)
      damlAppContext <- IO(new DamlAppContext(APP_ID, operatorParty, client))
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
        _ <- IO.never[Unit]
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
            IO.println("Invalid parameters\n" + errors.toList.mkString("\n")) *>
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

  def getOperatorParty(userManagementClient: UserManagementClient) = for {
    getUserResponse <- IO.blocking(
      userManagementClient
        .getUser(new GetUserRequest(OPERATOR_USER))
        .blockingGet()
    )
  } yield getUserResponse.getUser().getPrimaryParty().get()

  def getUserManagementClient(client: DamlLedgerClient) =
    IO(client.getUserManagementClient())

  def createClient(host: String, port: Int) = IO(
    DamlLedgerClient.newBuilder(host, port).build()
  )

  def connect(client: DamlLedgerClient) = IO(client.connect())


}
