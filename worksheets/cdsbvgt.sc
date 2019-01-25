
val a = List(0, 1, 3, 4, 6)


def go(acc: List[Int], l: List[Int]): List[Int] = {
  l match {
    case Nil => acc
    case fi :: se :: th :: tail =>
      val accum = if (th - fi == 2) fi :: th :: acc
      else fi :: se :: th :: acc
      go(accum, se :: th :: tail)
    case head :: tail => go(acc, l)
  }
}

go(Nil, a)