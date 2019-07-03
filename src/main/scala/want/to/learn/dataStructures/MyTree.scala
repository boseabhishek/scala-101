package want.to.learn.dataStructures

sealed trait MyTree
final case class Node(val l: MyTree, val r: MyTree) extends MyTree
final case class Leaf(val elt: Int) extends MyTree