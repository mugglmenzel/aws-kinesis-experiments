import scala.collection.immutable.HashMap

/**
  * Created by menzelmi on 27.09.16.
  */
object WordCounter {

  lazy val nonWord = "[^\\p{L}\\p{Nd}]+"

  def count(text: TraversableOnce[String]): Map[String, Int] =
    text.flatMap(_.split("\\s+"))
      .map(_.replaceAll(nonWord, "").toLowerCase)
      .filter(_.length > 0)
      .foldRight(HashMap.empty[String, Int])(
        (word, countsMap) => countsMap + (word -> (countsMap.getOrElse(word, 0) + 1))
      )

}
