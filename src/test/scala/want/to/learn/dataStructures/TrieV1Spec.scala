package want.to.learn.dataStructures

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper

class TrieV1Spec extends AnyFreeSpec {

  ".insert" - {
    "must insert values in the Trie" in {
      val trie = TrieV1[Int]
        .insert("to", 7)
        .insert("a", 15)
        .insert("tea", 3)
        .insert("ted", 4)
        .insert("ten", 12)
        .insert("i", 11)
        .insert("in", 5)
        .insert("inn", 9)

      println(s"------------------->>$trie")

      trie.search("ted") mustBe Some(4)
      /*trie.search("t") mustBe None
      trie.search("in") mustBe Some(5)*/
    }
  }

}
