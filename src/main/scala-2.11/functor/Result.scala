package functor

import scalaz.{Functor, Show}
import scalaz.std.anyVal._
import scalaz.syntax.show._

/**
 * Created by ysusuk on 15/11/15.
 */
trait Result[+A]

final case class Success[A](value: A) extends Result[A]

final case class Warning[A](value: A, message: String) extends Result[A]

final case class Failure(message: String) extends Result[Nothing]

object Result {
  implicit val resultFunctor: Functor[Result] = new Functor[Result] {
    override def map[A, B](fa: Result[A])(f: (A) => B): Result[B] = fa match {
      case Success(value) => Success(f(value))
      case Warning(value, msg) => Warning(f(value), msg)
      case flr @ Failure(msg) => flr
    }
  }

  implicit val resultStringShow: Show[Result[String]] = Show.shows { _.toString }
  implicit val resultIntShow: Show[Result[Int]] = Show.shows { _.toString }

  def success[A](value: A): Result[A] = Success(value)
  def warning[A](value: A, message: String): Result[A] = Warning(value, message)
  def failure[A](message: String): Result[A] = Failure(message)
}

object FunctorTest extends App {
  import scalaz.syntax.functor._
  import Result._

  def addChange(str: String) = str + " change"
  def mult2(num: Int) = num * 2

  (success("blah") map addChange).println
  (warning("blah", "msg") map addChange).println

  (success(10) map mult2).println
  (warning(10, "msg") map mult2).println
}