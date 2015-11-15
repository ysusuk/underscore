import java.util.Date

// "serialize to JSON"
trait Json

// 1. type class
trait JsonWriter[A] {
  def write(value: A): Json
}


object DefaultJsonWriters {
  // 2. type class instances
  implicit val stringJsonWriter = new JsonWriter[String] {
    override def write(value: String): Json = new Json{}
  }

  implicit val dateJsonWriter = new JsonWriter[Date] {
    override def write(value: Date): Json = new Json{}
  }

  trait Person

  implicit val personJsonWriter = new JsonWriter[Person] {
    override def write(value: Person): Json = new Json{}
  }
}

// interfaces

// Interface Objects

object Json {
  def toJson[A](value: A)(implicit writer: JsonWriter[A]): Json = {
    writer.write(value)
  }
}

// Interface Syntax
// type enrichment

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit writer: JsonWriter[A]): Json = {
      writer.write(value)
    }
  }
}

object blah extends App {
  import DefaultJsonWriters._
  import JsonSyntax._

  "blah".toJson
}