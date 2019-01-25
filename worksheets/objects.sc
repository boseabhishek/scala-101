case class Cat(colour: String, food: String)

object ChipShop {
  def willServe(cat: Cat) = {
    cat match {
      case Cat(_, "chips") => true
      case _ => false
    }
  }
}