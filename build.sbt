import Dependencies._


lazy val dockerPublishSettings = List(
   dockerExposedPorts ++= Seq(9000, 9001),
  Docker / dynverSeparator := "-",
  Docker / version := dynverGitDescribeOutput.value.mkVersion(versionFmt, fallbackVersion(dynverCurrentDate.value)),
  Docker / packageName := "bifrost-daml-broker",
  dockerBaseImage := "adoptopenjdk/openjdk11:jdk-11.0.16.1_1-ubuntu",
  dockerUpdateLatest := true
)

def versionFmt(out: sbtdynver.GitDescribeOutput): String = {
  val dirtySuffix = out.dirtySuffix.dropPlus.mkString("-", "")
  if (out.isCleanAfterTag) out.ref.dropPrefix + dirtySuffix // no commit info if clean after tag
  else out.ref.dropPrefix + out.commitSuffix.mkString("-", "-", "") + dirtySuffix
}


def fallbackVersion(d: java.util.Date): String = s"HEAD-${sbtdynver.DynVer timestamp d}"


lazy val mavenPublishSettings = List(
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
  .settings(if (sys.env.get("DOCKER_PUBLISH").getOrElse("false").toBoolean) dockerPublishSettings else mavenPublishSettings)
  .settings(
    name := "bifrost-daml-broker",
    scalaVersion := "2.13.8",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += brambl,
    libraryDependencies += bramblCommon,
    libraryDependencies += toplDaml,
    libraryDependencies += slf4j
  ).enablePlugins(DockerPlugin, JavaAppPackaging)
