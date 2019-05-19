package want.to.learn.functions

import common.HavingNormalMethods
import org.scalatest.{FreeSpec, MustMatchers}

class PartiallyAppliedFunctionsSpec extends FreeSpec with MustMatchers with HavingNormalMethods {

  "Partially applied functions could be created" - {

    "when a curried function is partially applied" in {

      //see the type (Int => Int => Boolean => Int)
      // just like in Haskell (Int -> Int -> Boolean -> Int)
      val curriedDecider: Int => Int => Boolean => Int = (normalDecider _).curried

      val curriedDeciderTruePAF: (Int, Int) => Int = curriedDecider(_: Int)(_: Int)(true)

      curriedDeciderTruePAF(5, 6) mustBe curriedDecider(5)(6)(true)

    }

    "when a normal method is partially applied" in {

      val normalDeciderTruePAF: (Int, Int) => Int = normalDecider(_: Int, _: Int, status = true)

      normalDeciderTruePAF(5, 6) mustBe normalDecider(5, 6, status = true)

    }
  }

}
