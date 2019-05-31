package common

trait HavingNormalMethods {

  ///normal Scala methods(say function)
  //all of them are of single param group
  def normalAdd(a: Int, b: Int): Int = a + b

  def normalMultiply(a: Int, b: Int): Int = a * b

  def normalDecider(x: Int, y: Int, status: Boolean): Int = if (status) x else y

  /*
     A generic function that will turn any binary operator
     into a curried function
   */
  def curryBinaryOperator[A](operator: (A, A) => A): A => (A => A) = {

    def curry(a: A): A => A = {
      (b: A) => operator(a, b)
    }

    curry
  }

}
