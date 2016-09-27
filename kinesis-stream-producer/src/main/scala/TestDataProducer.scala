import java.nio.ByteBuffer
import java.util.concurrent.Executors

import com.amazonaws.ClientConfiguration
import com.amazonaws.regions.Regions
import com.amazonaws.services.kinesis.model.{GetRecordsRequest, PutRecordRequest, Shard, ShardIteratorType}
import com.amazonaws.services.kinesis.{AmazonKinesis, AmazonKinesisClient, AmazonKinesisClientBuilder}
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

/**
  * Created by menzelmi on 26.09.16.
  */
case class TestDataProducer(streamName: String = "KinesisLab", dataSample: String = "test", region: Regions = Regions.EU_WEST_1, produceThrottle: Int = 0, proxy: Option[String] = None, proxyPort: Int = 8888, produceOnly: Boolean = false) {


  val kinesis: AmazonKinesis = proxy
    .fold[AmazonKinesis](new AmazonKinesisClient().withRegion(region).asInstanceOf[AmazonKinesis])(host =>
    AmazonKinesisClientBuilder.standard()
      .withClientConfiguration(new ClientConfiguration().withProxyHost(host).withProxyPort(proxyPort))
      .withRegion(region)
      .build()
  )
  val stream = kinesis.describeStream(streamName).getStreamDescription
  val shards = stream.getShards
  val hashKeys = stream.getShards.map(_.getHashKeyRange.getStartingHashKey)

  implicit val executionCtx = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(1 + shards.size()))

  lazy val logger = LoggerFactory.getLogger(getClass.getName)

  def printInfos = {
    println
    println("=" * 24)
    println("Stream Infos:")
    println("-------------")
    println(s"Name: ${stream.getStreamName}, Status: ${stream.getStreamStatus}, Retention: ${stream.getRetentionPeriodHours}h, Monitoring: ${stream.getEnhancedMonitoring}")
    shards.toIterable.foreach(shard => println(s"-- Shard: ${shard.getShardId}, Range: ${shard.getHashKeyRange}"))
    println("=" * 24)
    println

    this
  }

  def produce = {
    Future {
      while (true)
        hashKeys.foreach { hashKey =>
          lazy val dataBytes = "test".getBytes
          Try(
            kinesis.putRecord(
              new PutRecordRequest()
                .withStreamName(streamName)
                .withData(ByteBuffer.allocate(dataBytes.length).put(dataBytes))
                .withPartitionKey("partitionKey-" + hashKey)
                .withExplicitHashKey(hashKey)
            )
          ) match {
            case Success(put) =>
              println(s"PUT: ${put.getShardId}/${put.getSequenceNumber}")
            case Failure(e) =>
              logger.warn("Could not put record.", e)
          }

          Thread.sleep(produceThrottle)
        }
    }

    this
  }

  def consumeAllShards = {
    if (!produceOnly)
      shards.toIterable.foreach(consume)

    this
  }

  def consume(shard: Shard) = {
    if (!produceOnly)
      Future {
        var shardIterator = kinesis.getShardIterator(streamName, shard.getShardId, ShardIteratorType.LATEST.name()).getShardIterator
        while (true) {
          Try(
            kinesis.getRecords(
              new GetRecordsRequest()
                .withLimit(10)
                .withShardIterator(shardIterator)
            )
          ) match {
            case Success(records) =>
              shardIterator = records.getNextShardIterator
              records.getRecords.toIterable
                .foreach {
                  get =>
                    println(s"GET: ${shard.getShardId}/${get.getSequenceNumber} ${get.getPartitionKey}-${get.getData}")
                }
            case Failure(e) =>
              logger.warn("Could not get records.", e)
          }
        }
      }

    this
  }

}
