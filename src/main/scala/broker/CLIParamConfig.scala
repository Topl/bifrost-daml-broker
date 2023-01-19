package broker

import java.io.File

import co.topl.client.Provider

case class CLIParamConfigInput(
    networkType: String = "",
    networkUri: String = "",
    someApiKey: Option[String] = None,
    damlHost: String = "",
    damlPort: Int = 0,
    someKeyfile: Option[File] = None,
    somePassword: Option[String] = None
)

case class CLIParamConfigValidatedInput(
    provider: Provider,
    damlHost: String,
    damlPort: Int,
    keyfileAndPassword: Option[(File, String)]
)
