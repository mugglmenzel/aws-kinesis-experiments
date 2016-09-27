import scala.io.Source

/**
  * Created by menzelmi on 25.09.16.
  */
object FileWordCount extends App {

  lazy val file = Source.fromInputStream(getClass.getResourceAsStream("sample.txt"), "utf-8")

  WordCounter.count(file.getLines())
    .toSeq.sortWith(_._2 > _._2)
    .foreach(println)

}
