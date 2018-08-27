import org.slf4j.LoggerFactory

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession


class SparkDriver(conf: Config) {
  val log = LoggerFactory.getLogger(classOf[SparkDriver])

  lazy val sparkConf = new SparkConf().setAll(conf.spark)
  lazy val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
                  
  def init(): SparkSession = sparkSession
  
  def shutdown(): Unit = {
    sparkSession.stop()
  }

}

