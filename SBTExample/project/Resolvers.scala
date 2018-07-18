import sbt._
import Keys._

object Resolvers {

  val tconfrepo = "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
  
  //for local maven repo m2
  val localM2 = "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"


}
