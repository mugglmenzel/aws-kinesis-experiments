/**
  * Created by menzelmi on 26.09.16.
  */
object DataProducerApp extends App {

  TestDataProducer().printInfos.consumeAllShards.produce

}
