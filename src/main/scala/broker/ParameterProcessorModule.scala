package broker

import java.io.File

import akka.http.scaladsl.model.Uri
import cats.data.Validated
import cats.data.ValidatedNel
import co.topl.client.Provider
import scopt.OParser
import com.google.common.net.InetAddresses
import com.google.common.net.InternetDomainName

trait ParameterProcessorModule {

  val builder = OParser.builder[CLIParamConfigInput]
  val parser = {
    import builder._
    OParser.sequence(
      programName("bifrost-daml-broker"),
      head("bifrost-daml-broker", "0.2"),
      opt[String]('n', "topl-network")
        .action((x, c) => c.copy(networkType = x))
        .text(
          "the Topl network to connect to, one of: main, valhalla, and private"
        ),
      opt[String]('u', "topl-uri")
        .action((x, c) => c.copy(networkUri = x))
        .text("the URI of the Topl network to connect to, for example https://127.0.0.1/"),
      opt[Option[String]]('a', "topl-api-key")
        .action((x, c) => c.copy(someApiKey = x))
        .text("the API key for the Topl network"),
      opt[String]('h', "daml-host")
        .action((x, c) => c.copy(damlHost = x))
        .text("the host of the ledger, for example localhost"),
      opt[Int]('p', "daml-port")
        .action((x, c) => c.copy(damlPort = x))
        .text("the port where the ledger is listening, for example 6865"),
      opt[Option[File]]('k', "keyfile")
        .action((x, c) => c.copy(someKeyfile = x))
        .text("the file that contains the operator key, for example keyfile.json"),
      opt[Option[String]]('w', "password")
        .action((x, c) => c.copy(somePassword = x))
        .text("the password for the keyfile")
    )
  }

  def validateParams(
      paramConfig: CLIParamConfigInput
  ): ValidatedNel[String, CLIParamConfigValidatedInput] = {
    import cats.implicits._
    (
      (
        validateToplNetworkUri(paramConfig.networkUri),
        validateNetworkType(paramConfig.networkType)
      ).mapN((uri, networkType) =>
        buildNetwork(
          uri,
          networkType,
          paramConfig.someApiKey
        )
      ),
      validateDamlHost(paramConfig),
      validateDamlPort(paramConfig),
      validateFileAndPassword(paramConfig.someKeyfile, paramConfig.somePassword)
    ).mapN((provider, damlHost, damlPort, someFileAndPassword) =>
      CLIParamConfigValidatedInput(
        provider,
        damlHost,
        damlPort,
        someFileAndPassword
      )
    )
  }

  def validateFileAndPassword(
      someFile: Option[File],
      somePassword: Option[String]
  ) = (someFile, somePassword) match {
    case (Some(file), Some(password)) =>
      Validated.validNel(Some(file, password))
    case (Some(_), None) =>
      Validated.invalidNel("A password is required for the keyfile")
    case (None, Some(_)) => Validated.invalidNel("No keyfile provided")
    case (None, None)    => Validated.validNel(None)
  }

  def validateNetworkType(networkType: String) = {
    networkType match {
      case "main"     => Validated.validNel("main")
      case "valhalla" => Validated.validNel("valhalla")
      case "private"  => Validated.validNel("private")
      case _ =>
        Validated.invalidNel(
          "Invalid network type. Valid values are main, valhalla, and private"
        )
    }
  }

  def buildNetwork(
      uri: Uri,
      networkType: String,
      someApiKey: Option[String]
  ): Provider = {
    networkType match {
      case "main" =>
        new Provider.ToplMainNet(uri, someApiKey.getOrElse(""))
      case "valhalla" =>
        new Provider.ValhallaTestNet(uri, someApiKey.getOrElse(""))
      case "private" =>
        new Provider.PrivateTestNet(uri, someApiKey.getOrElse(""))
    }
  }
  def validateToplNetworkUri(networkUri: String): ValidatedNel[String, Uri] = {
    val invalidUriMessage = "Invalid Topl network URI"
    try {
      Uri(networkUri) match {
        case uri if uri.isAbsolute => Validated.validNel(uri)
        case _                     => Validated.invalidNel(invalidUriMessage)
      }
    } catch {
      case _: Throwable => Validated.invalidNel(invalidUriMessage)
    }
  }

  def validateDamlHost(
      paramConfig: CLIParamConfigInput
  ): ValidatedNel[String, String] = {
    paramConfig.damlHost match {
      case host
          if InetAddresses
            .isUriInetAddress(host) || InternetDomainName.isValid(host) =>
        Validated.validNel(host)
      case _ => Validated.invalidNel("Invalid Daml host")
    }
  }

  def validateDamlPort(
      paramConfig: CLIParamConfigInput
  ): ValidatedNel[String, Int] = {
    paramConfig.damlPort match {
      case port if port > 0 => Validated.validNel(port)
      case _                => Validated.invalidNel("Invalid Daml port")
    }
  }

}
