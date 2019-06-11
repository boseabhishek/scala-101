package want.to.learn.dataStructures

sealed trait MyList[A] {

  def sum: Int = this match {
    case End() => 0
    case Pair(hd, tl) => hd + tl.sum
  }

  def length: Int = this match {
    case End() => 0
    case Pair(hd, tl) => 1 + tl.length
  }

  def product: Int = this match {
    case End() => 1
    case Pair(hd, tl) => hd * tl.product
  }

  def double: MyList[A] = this match {
    case End() => End()
    case Pair(hd, tl) => Pair(hd * 2, tl.double)
  }

}

final case class End[A]() extends MyList[A]

final case class Pair[A](head: Int, tail: MyList[A]) extends MyList[A]
