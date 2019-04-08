  /*
	#application.conf
  application{
  
  metadata {
    driver="org.postgresql.Driver"
    url="jdbc:postgresql://127.0.0.1:5432/ayushTest"
    user="postgres"
    password="root"
    schema="dwmetadata"
  }

  cache {
    enable = false
    retention-Period = 24 hours
    //Sources to cached - LOGICAL, S3, HDFS, JDBC
    sources = ["LOGICAL","S3"]
  }
  
  }
  
  */


  
  import com.typesafe.config.ConfigFactory

  import java.util.concurrent.TimeUnit._

  import scala.collection.JavaConverters._

  val config = ConfigFactory.load()

  val app = config.getConfig("application")

  //list = ["a1","a2"]
  val list = app.getStringList("list").asScala.toList.map(_.toUpperCase())

  val metadata = app.getObject("metadata").unwrapped().asScala.toList.map {case(k,v) => ("metadata."+k, v.asInstanceOf[String])}.toMap

  val cacheConfig = app.getConfig("cache")

  lazy val retaintionPeriod = cacheConfig.getDuration("retention-Period", SECONDS)

  lazy val cacheEnable = cacheConfig.getBoolean("enable")

  lazy val cacheSources = cacheConfig.getStringList("sources").asScala.toList.map(_.toUpperCase())
