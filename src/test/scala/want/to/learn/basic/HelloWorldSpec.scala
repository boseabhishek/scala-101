package want.to.learn.basic

import org.scalatest.flatspec.AnyFlatSpec

class HelloWorldSpec extends AnyFlatSpec {

  "sayHello" should "return hello, abhi" in {
    val cn = new ClassName
    assert(cn.sayHello("abhi") == "hello, abhi")
  }

}
