name := "scala-101"

version := "0.1"


// https://docs.scala-lang.org/overviews/jdk-compatibility/overview.html#version-compatibility-table
// JDK - 18	| Scala - 3.1.3, 2.13.7, 2.12.15
// JDK - 17 (LTS)	| Scala - 3.0.0, 2.13.6, 2.12.15
scalaVersion := "2.12.16"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "joda-time" % "joda-time" % "2.12.5"
)