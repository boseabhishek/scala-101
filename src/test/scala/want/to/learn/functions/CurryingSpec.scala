package want.to.learn.functions

import common.HavingNormalMethods
import org.scalatest.{FreeSpec, MustMatchers}

/*
Currying is the technique of transforming a function with multiple arguments into a function with just one argument.
The single argument is the value of the first argument from the original function and the function returns another single argument function.
This in turn would take the second original argument and itself return another single argument function.
This chaining continues over the number of arguments of the original. The last in the chain will have access to all of the arguments and so can do whatever it needs to do.

You can turn any function with multiple arguments into it’s curried equivalen
 */

class CurryingSpec extends FreeSpec with MustMatchers with HavingNormalMethods {

  "curried functions" - {

    "can be created from a normal method in two steps" - {

      "1. transform it into a function and " +
        "2. invoke .curried on it" in {

        //could be done in the same step
        val functAdd: (Int, Int) => Int = normalAdd _
        val curriedAdd: Int => Int => Int = functAdd.curried

        curriedAdd.isInstanceOf[Function2[_, _, _]] mustBe false
      }
    }

    "gives the same result as normal methods" - {

      "when same params are passed" in {

        //using Scala's curried method in Function2 (as this is a two param function)
        val curriedAdd: Int => Int => Int = (normalAdd _).curried
        //looks like Haskell now

        //using custom method curryBinaryOperator in HavingNormalMethods trait which does the same as curried
        //but, limited to two param methods
        val curriedMultiply: Int => Int => Int = curryBinaryOperator(normalMultiply)

        normalAdd(5, 6) mustBe curriedAdd(5)(6)
        normalMultiply(5, 6) mustBe curriedMultiply(5)(6)

      }
    }

    "could be passed to another method which requires a curried function" in {

      val curriedAdd: Int => Int => Int = (normalAdd _).curried

      def aNormalMethod(a: Int, b: Int, curriedFunction: Int => Int => Int) = {
        curriedFunction(a)(b)
      }

      aNormalMethod(1, 2, curriedAdd) mustEqual 3
    }
  }


}
