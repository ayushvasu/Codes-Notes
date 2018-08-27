import scala.collection.convert.decorateAsScala._
import com.datastax.driver.core._
import com.datastax.driver.core.policies._

import scala.util.control.NonFatal

//import scala.collection.JavaConverters._
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CDriver(conf: Config) {

  val logger = LoggerFactory.getLogger("CDriver")

  private lazy val protocolVersion =  conf.cDriverProtocolVersion match {
    case 3 => ProtocolVersion.V3
    case 4 => ProtocolVersion.V4
  }

  private lazy val consistencyLevel = conf.cReadConsistencyLevel match {
    case "ONE" => ConsistencyLevel.ONE
    case "TWO" => ConsistencyLevel.TWO
    case "THREE" => ConsistencyLevel.THREE
    case "ALL" => ConsistencyLevel.ALL
    case _ => ConsistencyLevel.QUORUM
  }

  private lazy val queryOptions = new QueryOptions()
                       .setConsistencyLevel(consistencyLevel)
                       .setFetchSize(conf.cFetchSize)

  private lazy val socketOptions = new SocketOptions()
                       .setConnectTimeoutMillis(conf.cDriverConnectTimeoutMs)
                       .setReadTimeoutMillis(conf.cDriverReadTimeoutMs)
                       .setTcpNoDelay(conf.cDriverTcpNoDelay)

  private lazy val poolingOptions = new PoolingOptions()
                        .setCoreConnectionsPerHost(HostDistance.LOCAL, conf.cCoreConnectionsPerHostLocal)
                        .setMaxConnectionsPerHost(HostDistance.LOCAL, conf.cMaxConnectionsPerHostLocal)
                        .setCoreConnectionsPerHost(HostDistance.REMOTE, conf.cCoreConnectionsPerHostRemote)
                        .setMaxConnectionsPerHost(HostDistance.REMOTE, conf.cMaxConnectionsPerHostRemote)
                        .setMaxRequestsPerConnection(HostDistance.LOCAL, conf.cMaxRequestsPerConnectionLocal) // default is 1024
                        .setMaxRequestsPerConnection(HostDistance.REMOTE, conf.cMaxRequestsPerConnectionRemote)
                        .setIdleTimeoutSeconds(conf.cConnectionMaxIdleTimeSecs)

  private lazy val cluster = Cluster.builder()
                         .addContactPoints(conf.cSeeds: _*)
                         .withProtocolVersion(protocolVersion)
                         .withQueryOptions(queryOptions)
                         .withSocketOptions(socketOptions)
                         .withPoolingOptions(poolingOptions) // most options ignored with ProtocolVersion.V3. See Javadoc for class PoolingOptions
                         .withReconnectionPolicy(new ExponentialReconnectionPolicy(conf.cDriverReconnectBaseDelayMs, conf.cDriverReconnectMaxDelayMs))
                         .withLoadBalancingPolicy(new TokenAwarePolicy(new RoundRobinPolicy()))
                         .withRetryPolicy(new LoggingRetryPolicy(DefaultRetryPolicy.INSTANCE))
                         .build()

  private lazy val session = cluster.connect()


  def executeCQL(cqlStatement: String): ResultSet = {
    val ss = new SimpleStatement(cqlStatement)
    logger.debug("cql statement: {}", cqlStatement)
    session.execute(cqlStatement) 
  }

  def executeCQLAsync(cqlStatement: String): ResultSetFuture = {
    logger.debug("cql statement: {}", cqlStatement)
    session.executeAsync(cqlStatement)
  }

  def shutdown() {
    session.close()
    cluster.close()
  }

}

