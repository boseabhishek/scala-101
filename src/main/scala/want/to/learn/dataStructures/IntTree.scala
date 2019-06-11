package want.to.learn.dataStructures

sealed trait IntTree {
  def sum: Int = this match {
    case Leaf(elt) => elt
    case Node(left, right) => left.sum + right.sum
  }

  def double: IntTree = this match {
    case Leaf(elt) => Leaf(elt * 2)
    case Node(l, r) => Node(l.double, r.double)
  }
}
final case class Node(l: IntTree, r: IntTree) extends IntTree
final case class Leaf(elt: Int) extends IntTree
