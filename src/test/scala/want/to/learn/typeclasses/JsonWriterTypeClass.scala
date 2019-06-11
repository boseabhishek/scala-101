package want.to.learn.typeclasses

// Type class pattern example

case class Picture(name: String, uri: String)

case class Attachment(name: String, uri: String, visible: Boolean)

trait JsonWriter[T]{
  def toJson(in: T): String
}

object JsonWriter{
  def jsonOf[T](in: T)(implicit jsonSerializer: JsonWriter[T]): String =
    jsonSerializer.toJson(in)

  // Or this more succinct version
  //
  //def jsonOf[T: JsonWriter](in: T): String =
  //  implicitly[JsonWriter[T]].toJson(in)

  implicit val pictureJsonWriter = new JsonWriter[Picture]{
    override def toJson(in: Picture): String = s"picture ${in.name}"
  }

  implicit val attachmentJsonWriter = new JsonWriter[Attachment]{
    override def toJson(in: Attachment): String = s"attachment ${in.name}"
  }

}


