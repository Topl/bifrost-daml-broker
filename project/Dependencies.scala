import sbt._

object Dependencies {

  lazy val toplOrg = "co.topl"

  lazy val bramblVersion = "1.10.2"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.11"
  lazy val brambl = toplOrg %% "brambl" % bramblVersion
  lazy val bramblCommon = toplOrg %% "common" % bramblVersion
  lazy val catEffects = "org.typelevel" %% "cats-effect" % "3.3.12"
  lazy val toplDaml = toplOrg % "daml-bifrost-module" % "0.1.0"
  lazy val slf4j = "org.slf4j" % "slf4j-simple" % "2.0.5"
}