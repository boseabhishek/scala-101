
trait Wheel {
  def rolling = {
    println("wheel is rolling")
  }
}

trait Engine {
  def revving = {
    println("Engine is ignited!")
  }
}

trait Car {
  this: Wheel with Engine =>
  def driving = {
    println("driving the car!")
  }
}

class MiniVan extends Car with Wheel with Engine

val s = new MiniVan

s.driving
s.rolling
s.revving