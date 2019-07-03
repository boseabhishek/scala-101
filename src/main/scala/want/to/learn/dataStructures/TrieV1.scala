package want.to.learn.dataStructures

case class TrieV1[V](value: Option[V], children: List[Option[TrieV1[V]]]) {
  def insert(key: String, value: V): TrieV1[V] = TrieV1.insert(this, key, value, 0)

  def delete(key: String): TrieV1[V] = TrieV1.delete(this, key, 0)

  def search(key: String): Option[V] = TrieV1.search(this, key, 0)
}

object TrieV1 {
  def empty[V]: TrieV1[V] = new TrieV1[V](None, List.fill(26)(None))

  def apply[V]: TrieV1[V] = empty[V]

  private def search[V](node: TrieV1[V], key: String, step: Int): Option[V] =
    if (key.length == step) {
      node.value
    } else {
      node.children(key.charAt(step) - 97) match {
        case Some(nextItem) => search(nextItem, key, step + 1)
        case None => None
      }
    }

  private def insert[V](node: TrieV1[V], key: String, value: V, step: Int): TrieV1[V] =
    if (key.length == step) {
      node.copy(value = Some(value))
    } else {
      val index = key.charAt(step) - 97
      val nextItem = node.children(index).getOrElse(TrieV1.empty[V])
      val newNode = insert(nextItem, key, value, step + 1)
      val newNext = node.children.updated(index, Some(newNode))

      node.copy(children = newNext)
    }

  private def delete[V](node: TrieV1[V], key: String, step: Int): TrieV1[V] =
    if (key.length == step) {
      node.copy(value = None)
    } else {
      val index = key.charAt(step) - 97
      node.children(index) match {
        case None => node
        case Some(nextItem) =>
          val newNode = delete(nextItem, key, step + 1)
          val newChildren =
            if (newNode.value.isEmpty && newNode.children.forall(_.isEmpty))
              node.children.updated(index, None)
            else
              node.children.updated(index, Some(newNode))

          node.copy(children = newChildren)
      }
    }
}
