package algorithm
import scala.language.implicitConversions

case class Memo[K,I,O](f: I => O)(implicit i2k:I=>K ) extends (I => O) {
  import scala.collection.mutable.{Map => Dict}
  val cache = Dict.empty[K, O]
  override def apply(x: I) = cache getOrElseUpdate (x, f(x))
}
object Memo {
  /**
   * Type of a simple memoized function e.g. when I = K
   */
  type ==>[I, O] = Memo[I, I, O]
}
