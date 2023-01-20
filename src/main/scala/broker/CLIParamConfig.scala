package broker

import java.io.File

import co.topl.client.Provider

case class CLIParamConfigInput(
    networkType: String = "",
    networkUri: String = "",
    someApiKey: Option[String] = None,
    damlHost: String = "",
    damlPort: Int = 0,
    damlSecurityEnabled: Boolean = false,
    damlAccessToken: Option[String] = None,
    damlOperatorParty: String = "",
    someKeyfile: Option[File] = None,
    somePassword: Option[String] = None
)

case class CLIParamConfigValidatedInput(
    provider: Provider,
    damlHost: String,
    damlPort: Int,
    damlSecurityEnabled: Boolean,
    damlAccessToken: Option[String],
    damlOperatorParty: String,
    keyfileAndPassword: Option[(File, String)]
)
