package want.to.learn.dataStructures

sealed trait TrieV2 {

  /**
    *
    * @param word String to add to the trie
    * @return Returns the immutable new trie got created after the string addition
    */
  def add(word: String): TrieV2 = {

    def navigate(rest: List[Char], currentNode: TrieV2): TrieV2 = (rest, currentNode) match {
      case (Nil, node: TrieV2Node) => TrieV2Node(node.next, wordEnding = true)
      case (Nil, Empty) => Empty
      case (x :: xs, Empty) => TrieV2Node(Map(x -> navigate(xs, Empty)))
      case (x :: xs, TrieV2Node(nextMap, _)) if nextMap contains x =>
        TrieV2Node(nextMap + (x -> navigate(xs, nextMap(x))))
      case (x :: xs, TrieV2Node(nextMap, _)) => TrieV2Node(nextMap + (x -> navigate(xs, Empty)))
    }

    navigate(word.toLowerCase.toList, this)
  }

  /**
    * @param word String to search in the trie
    * @return returns true if the word is found in the trie
    */
  def find(word: String): Boolean = {

    @scala.annotation.tailrec
    def navigate(rest: List[Char], currentNode: TrieV2): Boolean = (rest, currentNode) match {
      case (Nil, Empty) => true
      case (Nil, node: TrieV2Node) if node.wordEnding => true
      case (_, Empty) => false
      case (c :: cs, TrieV2Node(nextMap, _)) if nextMap contains c => navigate(cs, nextMap(c))
      case (_, _) => false
    }

    navigate(word.toLowerCase.toList, this)
  }


  /**
    *
    * @return all words represented by the trie
    */
  def words: List[String] = {

    def navigate(currentNode: TrieV2): List[String] = currentNode match {
      case TrieV2Node(next, _) => next.map {
        case (k, Empty) => List(k.toString)
        case (k, deepNode: TrieV2Node) if deepNode.wordEnding =>
          ("" :: navigate(deepNode)).map(str => (k :: str.toList).mkString)
        case (k, deepNode) => navigate(deepNode).map(str => (k :: str.toList).mkString)
      }.foldLeft(List.empty[String])(_ ++ _)
      case Empty => List.empty[String]
    }

    navigate(this)
  }

}

case class TrieV2Node(next: Map[Char, TrieV2], wordEnding: Boolean = false) extends TrieV2
case object Empty extends TrieV2
