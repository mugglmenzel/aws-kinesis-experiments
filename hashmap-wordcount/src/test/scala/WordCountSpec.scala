import org.scalatest.FreeSpec

/**
  * Created by menzelmi on 27.09.16.
  */
class WordCountSpec extends FreeSpec {

  "Check word count returns correct counts" in {
    val sample = Seq("ABC bac foo abc foo foo foo")
    val expect = Map("foo" -> 4, "abc" -> 2, "bac" -> 1)

    assert(WordCounter.count(sample) equals expect)
  }

}
