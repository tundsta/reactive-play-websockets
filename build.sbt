name := """ratwd"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  PlayKeys.ws,
  "org.webjars" %% "webjars-play" % "2.3-M1",
  "org.webjars" % "bootstrap" % "3.1.1",
  "org.webjars" % "angularjs" % "1.2.16"
)

lazy val root = (project in file(".")).addPlugins(PlayScala)
