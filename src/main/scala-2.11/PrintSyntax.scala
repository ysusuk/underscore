object PrintSyntax {

  implicit class PrintObs[A](value: A) {
    def format(implicit printable: Printable[A]): String = printable.format(value)

    def print(implicit printable: Printable[A]): Unit = println(printable.format(value))
  }
}

object SyntaxTest extends App {

  import PrintableDefaults._
  import PrintSyntax._

  println("blahblahblah".format())
  println(123.format)
  "blah".print
  456.print
  Cat("Happy", 1, "rainbow").print
}
