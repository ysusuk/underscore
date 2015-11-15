// type class
trait Printable[A] {
  def format(value: A): String
}

// type class instances
object PrintableDefaults {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
    override def format(value: String): String = value.toString
  }

  implicit val intPrintable: Printable[Int] = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }

  case class Cat(name: String, age: Int, color: String)

  // good place will be as well Cat companion object
  implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
  }
}

// interfaces
object Print {

  def format[A](value: A)(implicit printable: Printable[A]): String = {
    printable.format(value)
  }

  def print[A](value: A)(implicit printable: Printable[A]): Unit = {
    println(printable.format(value))
  }
}

object ObjectTest extends App {

  import PrintableDefaults._
  import Print._

  println(format("blahblah"))

  println(format(123123))

  println(format(Cat("happy", 1, "rainbow")))
}