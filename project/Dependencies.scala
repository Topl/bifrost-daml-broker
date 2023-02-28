import sbt._

object Dependencies {

  lazy val toplOrg = "co.topl"


  lazy val bramblVersion = "1.10.2"
  lazy val http4sVersion = "0.23.18"
  lazy val brambl = toplOrg %% "brambl" % bramblVersion
  lazy val bramblCommon = toplOrg %% "common" % bramblVersion
  lazy val catEffects = "org.typelevel" %% "cats-effect" % "3.3.12"
  lazy val toplDaml = toplOrg % "daml-bifrost-module" % "0.1.0"
  lazy val scopt = "com.github.scopt" %% "scopt" % "4.0.1"
  lazy val munit = "org.scalameta" %% "munit" % "0.7.29" % Test
  lazy val fs2Core = "co.fs2" %% "fs2-core" % "3.5.0"
  lazy val fs2IO = "co.fs2" %% "fs2-io" % "3.5.0"
  lazy val fs2ReactiveStreams = "co.fs2" %% "fs2-reactive-streams" % "3.5.0"
  lazy val http4sEmber = "org.http4s" %% "http4s-ember-client" % http4sVersion
  lazy val http4sCirce = "org.http4s" %% "http4s-circe" % http4sVersion
  // Optional for auto-derivation of JSON codecs
  lazy val http4sCirceGeneric ="io.circe" %% "circe-generic" % "0.14.3",
}
