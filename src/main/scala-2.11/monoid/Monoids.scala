package monoid

import scalaz.Monoid
import scalaz.Show
import scalaz.Equal
import scalaz.std.anyVal._
import scalaz.std.option._
import scalaz.syntax.monoid._
import scalaz.syntax.show._
import scalaz.syntax.equal._

/**
 * Created by ysusuk on 15/11/15.
 */
object Monoids {

  implicit val booleanAndMonoid: Monoid[Boolean] = Monoid.instance[Boolean] (_ && _, z = true)

  implicit val booleanOrMonoid: Monoid[Boolean] = Monoid.instance[Boolean](_ || _, z = false)

  implicit def setUnionMonoid[A]: Monoid[Set[A]] = Monoid.instance[Set[A]](_ union _, z = Set.empty[A])
}

object TestMonoid extends App {
  val res1 = (some(1) |+| some(2) |+| none[Int])

  val res2 = for {
    a <- some(1)
    b <- some(2)
  } yield (a + b)

  (res1 === res2).println
}