package week4

/**
  * Created by zhao on 12/25/16.
  */
trait List[+T] {
  //Covariant type

  def isEmpty: Boolean

  def head: T

  def tail: List[T]

  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
}

/**
  * val override method from List, only difference between val and def is initialization.
  * val is evaluated when the object is first initialized whereas def is evaluated
  * each time it is referenced.
  *
  * @param head
  * @param tail
  * @tparam T
  */
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

object Nil extends List[Nothing] {
  def isEmpty: Boolean = true

  def head: Nothing = throw new NoSuchElementException("Nil.head")

  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object List {
  //  Like classes , functions can have type parameters
  def singleton[T](elem: T) = new Cons[T](elem, Nil)

  singleton[Int](1)
  singleton[Boolean](true)
  singleton(false)
  singleton(3.0)
}

object test {
  val x: List[String] = Nil
  val y: List[Double] = Nil
}

