name := "scala-colle"

version := "1.0"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

libraryDependencies ++= Seq(
  "org.specs2" % "specs2-core_2.11" % "3.6.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.4" % "test"
)

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
