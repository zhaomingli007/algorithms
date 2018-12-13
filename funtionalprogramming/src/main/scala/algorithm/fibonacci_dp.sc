import scala.collection.mutable.Map
import scala.language.implicitConversions
implicit def i2b(i:Int):BigInt = BigInt(i)
def fbnc(x:Int):BigInt = {
  val cache = Map.empty[Int,BigInt]
  def f(n:Int):BigInt = if(n <= 1) n else cache.getOrElseUpdate(n,f(n-1)+f(n-2))
  f(x)
}
val a = fbnc(12)

//Use memo
import Memo._

lazy val fib: Int ==> BigInt = Memo {
  case x if x <= 1 => x
  case x if x > 1 => fib(x-1)+fib(x-2)
}

fib(12)
