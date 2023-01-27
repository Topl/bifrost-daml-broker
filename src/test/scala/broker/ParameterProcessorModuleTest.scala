package broker

import java.io.File

// an m-unit test
class ParameterProcessorModuleTest
    extends munit.FunSuite
    with ParameterProcessorModule {

  test("Test with None keyfile and password") {
    val input = CLIParamConfigInput(
      networkType = "main",
      networkUri = "https://api.topl.co",
      someApiKey = Some("some-api-key"),
      damlHost = "localhost",
      damlPort = 6865,
      damlOperatorParty = "party::abcdefg",
      someKeyfile = None,
      somePassword = None
    )

    val validatedInput = validateParams(input)

    assertEquals(validatedInput.isValid, true)
  }
  test("Test invalid network type") {
    val input = CLIParamConfigInput(
      networkType = "mainnet",
      networkUri = "https://api.topl.co",
      someApiKey = Some("some-api-key"),
      damlHost = "localhost",
      damlPort = 6865,
      damlOperatorParty = "party::abcdefg",
      someKeyfile = None,
      somePassword = None
    )

    val validatedInput = validateParams(input)

    assertEquals(validatedInput.isInvalid, true)
    assertEquals(
      validatedInput.fold(_.toList, _ => Nil),
      List("Invalid network type. Valid values are main, valhalla, and private")
    )
  }
  test("Test invalid network uri") {
    val input = CLIParamConfigInput(
      networkType = "main",
      networkUri = "this is an invalid uri",
      someApiKey = Some("some-api-key"),
      damlHost = "localhost",
      damlPort = 6865,
      damlOperatorParty = "party::abcdefg",
      someKeyfile = None,
      somePassword = None
    )

    val validatedInput = validateParams(input)

    assertEquals(validatedInput.isInvalid, true)
    assertEquals(
      validatedInput.fold(_.toList, _ => Nil),
      List("Invalid Topl network URI")
    )
  }

  test("Test invalid daml host") {
    val input = CLIParamConfigInput(
      networkType = "main",
      networkUri = "https://api.topl.co",
      someApiKey = Some("some-api-key"),
      damlHost = "this is an invalid host",
      damlPort = 6865,
      damlOperatorParty = "party::abcdefg",
      someKeyfile = None,
      somePassword = None
    )

    val validatedInput = validateParams(input)

    assertEquals(validatedInput.isInvalid, true)
    assertEquals(
      validatedInput.fold(_.toList, _ => Nil),
      List("Invalid Daml host")
    )
  }

  test("Test invalid daml port") {
    val input = CLIParamConfigInput(
      networkType = "main",
      networkUri = "https://api.topl.co",
      someApiKey = Some("some-api-key"),
      damlHost = "localhost",
      damlPort = -1,
      damlOperatorParty = "party::abcdefg",
      someKeyfile = None,
      somePassword = None
    )

    val validatedInput = validateParams(input)

    assertEquals(validatedInput.isInvalid, true)
    assertEquals(
      validatedInput.fold(_.toList, _ => Nil),
      List("Invalid Daml port")
    )
  }

  test("Test missing password") {
    val input = CLIParamConfigInput(
      networkType = "main",
      networkUri = "https://api.topl.co",
      someApiKey = Some("some-api-key"),
      damlHost = "localhost",
      damlPort = 6865,
      damlOperatorParty = "party::abcdefg",
      someKeyfile = Some(new File("some-keyfile.json")),
      somePassword = None
    )

    val validatedInput = validateParams(input)

    assertEquals(validatedInput.isInvalid, true)
    assertEquals(
      validatedInput.fold(_.toList, _ => Nil),
      List("A password is required for the keyfile")
    )
  }

  test("Test missing keyfile") {
    val input = CLIParamConfigInput(
      networkType = "main",
      networkUri = "https://api.topl.co",
      someApiKey = Some("some-api-key"),
      damlHost = "localhost",
      damlPort = 6865,
      damlOperatorParty = "party::abcdefg",
      someKeyfile = None,
      somePassword = Some("some-password")
    )

    val validatedInput = validateParams(input)

    assertEquals(validatedInput.isInvalid, true)
    assertEquals(
      validatedInput.fold(_.toList, _ => Nil),
      List("No keyfile provided")
    )
  }
  
  test("Invalid party") {
    val input = CLIParamConfigInput(
      networkType = "main",
      networkUri = "https://api.topl.co",
      someApiKey = Some("some-api-key"),
      damlHost = "localhost",
      damlPort = 6865,
      damlOperatorParty = "",
      someKeyfile = None,
      somePassword = None
    )

    val validatedInput = validateParams(input)

    assertEquals(validatedInput.isInvalid, true)
    assertEquals(
      validatedInput.fold(_.toList, _ => Nil),
      List("No operator party provided")
    )
  }



}
