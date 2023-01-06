import Dependencies._

lazy val dockerPublishSettings = List(
  dockerExposedPorts ++= Seq(9000, 9001),
  git.useGitDescribe := true,
  Docker / packageName := "bifrost-daml-broker",
  dockerBaseImage := "adoptopenjdk/openjdk11:jdk-11.0.8_10-ubuntu",
  dockerUpdateLatest := true
)

lazy val publishSettings = List(
  organization := "co.topl",
  homepage := Some(url("https://github.com/Topl/bifrost-daml-broker")),
  licenses := List("MPL2.0" -> url("https://www.mozilla.org/en-US/MPL/2.0/")),
  sonatypeCredentialHost := "s01.oss.sonatype.org",
  sonatypeRepository := "https://s01.oss.sonatype.org/service/local",
  developers := List(
    Developer(
      "mundacho",
      "Edmundo Lopez Bobeda",
      "e.lopez@topl.me",
      url("https://github.com/mundacho")
    ),
    Developer(
      "scasplte2",
      "James Aman",
      "j.aman@topl.me",
      url("https://github.com/scasplte2")
    )
    )
)


lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  .settings(dockerPublishSettings)
  .settings(publishSettings)
  .settings(
    name := "bifrost-daml-broker",
    scalaVersion := "2.13.8",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += brambl,
    libraryDependencies += bramblCommon,
    libraryDependencies += toplDaml,
    libraryDependencies += slf4j
  ).enablePlugins(DockerPlugin, JavaAppPackaging)
