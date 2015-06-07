name := "scala-colle"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "2.4.15" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.3" % "test"
)

scalacOptions ++= Seq("-feature")
