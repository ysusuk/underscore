package show

import scalaz.Show
import scalaz.std.string._
import scalaz.std.anyVal._
import scalaz.syntax.show._

/**
 * Created by ysusuk on 14/11/15.
 */
case class Cat(name: String, age: Int, color: String)

object Cat {

  implicit val catShow: Show[Cat] = Show.shows[Cat] { cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show
    s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
  }
}