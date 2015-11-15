package monoid

/**
 * Created by ysusuk on 15/11/15.
 */
import scalaz.{Show, Monoid}
import scalaz.std.anyVal._
import scalaz.std.option._
import scalaz.syntax.monoid._
import scalaz.syntax.show._

// totalCost * quantity
case class Order(totalCost: Double, quantity: Double)

object SuperAdder {

  implicit val orderMonoid: Monoid[Order] = {
    def append(a: Order, b: => Order) = Order(a.totalCost + b.totalCost, a.quantity + b.quantity)
    val zero = Order(0, 0)
    Monoid.instance[Order](append, zero)
  }

  implicit val orderShow: Show[Order] = Show.shows(_.toString)
}

object TestSuperAdder extends App {
  import SuperAdder._

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A = {
    items.fold(mzero[A])(_ |+| _)
  }

  add(List(Order(1, 1), Order(1, 1))).println
}