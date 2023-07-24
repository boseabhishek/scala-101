package want.to.learn.basic

class ClassName {
  def sayHello(str: String): String = "hello, " + str
}

object Hello {
  def main(args: Array[String]) = {
    val cn = new ClassName
    println( "Returned Value : " + cn.sayHello("abhi") )
  }
}