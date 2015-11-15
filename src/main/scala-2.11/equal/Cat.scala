package equal

import show.Cat

import scalaz.Equal
import scalaz.syntax.equal._

/**
 * Created by ysusuk on 14/11/15.
 */
object Cat {

  implicit val catEqual: Equal[Cat] = Equal.equal { (cat1: Cat, cat2: Cat) =>
    import scalaz.std.anyVal._
    import scalaz.std.string._

    (cat1.name === cat2.name) &&
      (cat1.age === cat2.age) &&
      (cat2.color === cat2.color)
  }
}

object Main extends App {
  import Cat._

  val cat1 = show.Cat("Garfield", 35, "orange and black")
  val cat2 = show.Cat("Heathcliff", 30, "orange and black")

  val optionCat1: Option[show.Cat] = Some(cat1)
  val optionCat2: Option[show.Cat] = None

  println("cat1 === cat2 : " + (cat1 === cat2))
  println("cat1 =/= cat2 : " + (cat1 =/= cat2))
  // Bring Equal[Option] into scope for some further tests:
  import scalaz.std.option._
  println("optionCat1 === optionCat2 : " + (optionCat1 === optionCat2))
  println("optionCat1 =/= optionCat2 : " + (optionCat1 =/= optionCat2))
}