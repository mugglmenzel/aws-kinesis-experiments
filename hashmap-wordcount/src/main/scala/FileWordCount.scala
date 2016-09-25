import scala.collection.immutable.HashMap
import scala.io.Source

/**
  * Created by menzelmi on 25.09.16.
  */
object FileWordCount extends App {

  lazy val file = Source.fromInputStream(getClass.getResourceAsStream("sample.txt"), "utf-8")

  lazy val nonWord = "[^\\p{L}\\p{Nd}]+"


  lazy val map =
    file.getLines()
      .flatMap(_.split("\\s+"))
      .map(_.replaceAll(nonWord, "").toLowerCase)
      .filter(_.length > 0)
      .foldRight(HashMap.empty[String, Int])(
        (word, countsMap) => countsMap + (word -> (countsMap.getOrElse(word, 0) + 1))
      )

  map
    .toSeq.sortWith(_._2 > _._2)
    .foreach(println)

}
