import sbt._
import Keys._

object Dependencies {
  val sparkVersion = "2.2.0"
  val akkaVersion = "2.3.16"
  val scalaTestVersion = "3.0.5"
  val configVersion = "1.3.1"

  val scalatest = "org.scalatest" %% "scalatest" % scalaTestVersion
  val typeConfig = "com.typesafe" % "config" % configVersion

  val sparkCore = "org.apache.spark" % "spark-core_2.11" % sparkVersion
  val sparkSql = "org.apache.spark" % "spark-sql_2.11" % sparkVersion

  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion



}
