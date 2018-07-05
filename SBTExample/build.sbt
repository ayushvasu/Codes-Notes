import Resolvers._
import Dependencies._

// factor out common settings into a sequence
lazy val buildSettings = Seq(
  organization := "com.example",
  version := "0.1.0",
  scalaVersion := "2.11.8"
)

// Sub-project specific dependencies
lazy val sparkDeps = Seq(
  sparkCore,
  sparkSql,
  typeConfig,
  scalatest % Test
)

// Sub-project specific dependencies
lazy val akkaDeps = Seq(
  akkaActor,
  akkaSlf4j,
  scalatest % Test
)

lazy val cdap = (project in file("."))
  .aggregate(sparkExample,akkaExample)
  .settings(buildSettings)

lazy val sparkExample = (project in file("SparkExample"))
  .settings(
    buildSettings,
    resolvers += tconfrepo,
    libraryDependencies ++= sparkDeps
  )

lazy val akkaExample = (project in file("AkkaExample"))
  .dependsOn(sparkExample)
  .settings(
    buildSettings,
    libraryDependencies ++= akkaDeps
  )