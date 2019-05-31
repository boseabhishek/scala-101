package want.to.learn.functions

import common.HavingNormalMethods
import org.scalatest.{FreeSpec, MustMatchers}

class TransformIntoFunctionSpec extends FreeSpec with MustMatchers with HavingNormalMethods {

  "normal methods" - {

    "can be transformed into a function" - {

      "when _ is applied to it" in {

        (normalAdd _).isInstanceOf[Function2[_, _, _]] mustBe true
      }
    }

    "can be used as a function" - {

      "when applied with params the same way as methods" in {

        //This is Eta Expansion - nothing like Currying
        val normalAddFunction: (Int, Int) => Int = normalAdd _

        normalAddFunction(5, 6) mustBe normalAdd(5, 6)
      }
    }


    "of when transformed into a function of type A => A" - {

      def f(s: String) = "f(" + s + ")"
      def g(s: String) = "g(" + s + ")"

      val functionf: String => String = f _
      val functiong: String => String = g _

      "can be composed" in {
        val composed: String => String = functionf compose functiong
        composed("The String") must be("f(g(The String))")
      }

      "can be andThen" in {
        val andThened = functionf andThen functiong
        andThened("The String") must be("g(f(The String))")
      }
    }
  }

}
