import scala.language.implicitConversions
import scala.util.Try
import scala.util.Success
import scala.util.Failure
object context_bound{


  def main(args: Array[String]): Unit = {
    //Implicit Conversion (implicit conversion from String to StringOps which has map method)
  //  "abc".map(_.toInt)
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

    println(getIndex("abc", 'b'))


  }
}
