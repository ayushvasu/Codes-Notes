import com.typesafe.config._

import scala.collection.JavaConverters._

import java.time.format.DateTimeFormatter
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit._
import java.time.temporal.ChronoUnit

class Config {
  val log = org.slf4j.LoggerFactory.getLogger(classOf[Config])
  val config = ConfigFactory.load()
  val app = config.getConfig("s-c2r")
  val dataSources = app.getConfig("data-sources")
  val srcDataSource = dataSources.getConfig("src")
  val dstDataSource = dataSources.getConfig("dst")

  val spark = app.getObject("spark").unwrapped().asScala.toList.map {case(k,v) =>
                                                                      (k, v.asInstanceOf[String])}
  
  val scope = app.getConfigList("scope").asScala.toList.map{ config =>
                    (config.getString("mfr"), 
                     config.getString("prod"), 
                     config.getString("sch"), 
                     config.getString("ec"), 
                     config.getStringList("cfs").asScala.toList,
                     config.getString("schema")
                    )
                  }
  val tblConfig = app.getConfig("tables")
  val evtTblConfig = tblConfig.getConfig("event_tbl")
  val statTblConfig = tblConfig.getConfig("stat_tbl")
  val sessTblConfig = tblConfig.getConfig("sess_tbl")
  val alertTblConfig = tblConfig.getConfig("alert_tbl")
  
  val evtExcludeTbls = evtTblConfig.getStringList("exclude_tbls").asScala.toList
  val evtExcludeCols = evtTblConfig.getStringList("exclude_cols").asScala.toList
  
  val statExcludeTbls = statTblConfig.getStringList("exclude_tbls").asScala.toList
  val statExcludeCols = statTblConfig.getStringList("exclude_cols").asScala.toList
  
  val sessExcludeTbls = sessTblConfig.getStringList("exclude_tbls").asScala.toList
  val sessExcludeCols = sessTblConfig.getStringList("exclude_cols").asScala.toList
  
  val alertIncludeCols = alertTblConfig.getStringList("include_cols").asScala.toList
  
  val srcFormat = srcDataSource.getString("format")

  val dstFormat = dstDataSource.getString("format")
  val dstMode = dstDataSource.getString("mode")
  val dstOptions = dstDataSource.getObject("options").unwrapped().asScala.toMap.mapValues{_.asInstanceOf[String]}
  
  val metaKS = app.getString("metadata-ks")

  val dfMergeBatchSize = app.getInt("dataframe-merge-batch-size")
  val maxEtlTimeHours = app.getDuration("max-etl-time", HOURS)
  
  val manualRun = app.getBoolean("manual-run")
  val updateLoaded = app.getBoolean("update-loaded")
  val endRxDateStr = app.getString("end-rx-date")
  val startRxDate = ZonedDateTime.parse(app.getString("start-rx-date"))
  val endRxDate = if(endRxDateStr.equals("")) ZonedDateTime.now(ZoneId.of("GMT")).truncatedTo(ChronoUnit.DAYS)
                  else ZonedDateTime.parse(endRxDateStr)
  
  lazy val cConfig = app.getConfig("cassandra")

  lazy val cSeeds = cConfig.getStringList("seeds").asScala
  lazy val cDriverReadTimeoutMs = cConfig.getDuration("driver-read-timeout", MILLISECONDS).toInt
  lazy val cDriverConnectTimeoutMs = cConfig.getDuration("driver-connect-timeout", MILLISECONDS).toInt
  lazy val cDriverTcpNoDelay = cConfig.getBoolean("driver-tcp-no-delay")
  lazy val cDriverReconnectBaseDelayMs = cConfig.getDuration("driver-reconnect-base-delay", MILLISECONDS).toInt
  lazy val cDriverReconnectMaxDelayMs = cConfig.getDuration("driver-reconnect-max-delay", MILLISECONDS).toInt
  lazy val cDriverProtocolVersion = cConfig.getInt("driver-protocol-version")
  lazy val cReadConsistencyLevel = cConfig.getString("read-consistency-level").toUpperCase
  lazy val cFetchSize = cConfig.getInt("fetch-size")
  lazy val cCoreConnectionsPerHostLocal = cConfig.getInt("core-connections-per-host-local")
  lazy val cCoreConnectionsPerHostRemote = cConfig.getInt("core-connections-per-host-remote")
  lazy val cMaxConnectionsPerHostLocal = cConfig.getInt("max-connections-per-host-local")
  lazy val cMaxConnectionsPerHostRemote = cConfig.getInt("max-connections-per-host-remote")
  lazy val cMaxRequestsPerConnectionLocal = cConfig.getInt("max-requests-per-connection-local")
  lazy val cMaxRequestsPerConnectionRemote = cConfig.getInt("max-requests-per-connection-remote")
  lazy val cConnectionMaxIdleTimeSecs = cConfig.getDuration("connection-max-idle-time-seconds", SECONDS).toInt 
  lazy val cPartitionRowReadCount = cConfig.getInt("partition-row-read-count")
  lazy val cPartitionStatTimeDiff = cConfig.getDuration("partition-stat-time-diff", MILLISECONDS).toLong

  lazy val cReadRetries = cConfig.getInt("read-retries")
  lazy val cReadRetriesWithinTime = cConfig.getDuration("read-retries-within-time", MINUTES).toInt
  lazy val cReadDelayBetweenRetriesSecs = cConfig.getDuration("read-delay-between-retries", SECONDS).toInt

}

