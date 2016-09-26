import com.amazonaws.regions.Regions
import scopt.OptionParser

/**
  * Created by menzelmi on 26.09.16.
  */
object DataProducerApp extends App {

  implicit val regionsRead: scopt.Read[Regions] =
    scopt.Read.reads(Regions fromName)

  new OptionParser[TestDataProducer]("ktdp"){
    head("Kinesis Test Data Prosumer (ktdp)", "1.0")
    opt[String]('n', "stream-name").required().action((value, producer) => producer.copy(streamName = value))
    opt[String]('d', "data").optional().action((value, producer) => producer.copy(dataSample = value))
    opt[Regions]('r', "region").optional().action((value, producer) => producer.copy(region = value))
    opt[Int]('t', "produce-throttle").optional().action((value, producer) => producer.copy(produceThrottle = value))
    opt[String]('p', "proxy").optional().action((value, producer) => producer.copy(proxy = Some(value)))
    opt[Int]('P', "proxy-port").optional().action((value, producer) => producer.copy(proxyPort = value))
  }.parse(args, TestDataProducer()) match {
    case Some(producer) =>
      producer.printInfos.consumeAllShards.produce
    case None =>
  }

}
