package want.to.learn.dataStructures

import org.scalatest.{FreeSpec, MustMatchers}

class MyListSpec extends FreeSpec with MustMatchers {

  val intList = Pair(1, Pair(2, Pair(3, End())))

  ".sum must" - {
    "return the sum of the elments in IntList" in {
      intList.sum mustBe 6
      intList.tail.sum mustBe 5
      End().sum mustBe 0
    }
  }

  ".length must" - {
    "return the length of IntList" in {
      intList.length mustBe 3
      intList.tail.length mustBe 2
      End().length mustBe 0
    }
  }

  ".product must" - {
    "return the product of the elements in IntList" in {
      intList.product mustBe 6
      intList.tail.product mustBe 6
      End().product mustBe 1
    }
  }

  ".double must" - {
    "return doubled the elements in the IntList" in {
      intList.double mustBe Pair(2, Pair(4, Pair(6, End())))
      intList.tail.double mustBe Pair(4, Pair(6, End()))
      End().double mustBe End()
    }
  }
}
