import scala.language.implicitConversions
import scala.util.Try
import scala.util.Success
import scala.util.Failure
//Implicit Conversion (implicit conversion from String to StringOps which has map method)
// "abc".map(_.toInt)
//Implicit Parameter
implicit def string2Int(s: String): Try[Int] = Try(augmentString(s).toInt)
implicit def int2Int(t: Int): Try[Int] = Success(t)
def foo[T](x:T)(implicit y: T=>Try[Int])={
  y(x) match {
    case Success(r) => println(r)
    case Failure(e) => println(e.getMessage)
  }
}
foo("123")
foo("abc")
foo(12)


//Implicint conversion as parameter
def getIndex[S,T](seq: S, value: T)(implicit conv: S => Seq[T] ) = seq.indexOf(value)
val idx = getIndex("abc", 'a')
println(idx)
//Type class with Integral
def sum[T](list:List[T])(implicit imp:Integral[T]):T={
  import imp._
  list.foldLeft(imp.zero)(_+_)
}
sum(List(1,2,3))
//Syntactic sugur: context bound
def sum2[T:Integral](list:List[T]):T={
  val imp = implicitly[Integral[T]]
  import imp._
  list.foldLeft(imp.zero)(_+_)
}
sum2(1::2::3::4::5::Nil)
