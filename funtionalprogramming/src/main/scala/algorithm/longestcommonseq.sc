import scala.language.implicitConversions
import Memo._
def lcs[A](a: List[A], b: List[A]) = {

  type DP = Memo[(Int,Int),(List[A],List[A]),List[A]]
  implicit def encode(x:(List[A],List[A])):(Int,Int) = (x._1.length,x._2.length)

  implicit val c: Ordering[List[A]] = Ordering.by(_.length)
  lazy val f:DP = Memo{
    case (Nil,_) | (_,Nil) => Nil
    case (x:: xl,y::yl) if x == y =>x::f(xl,yl)
    case (x,y) => c.max(f(x.tail,y), f(x,y.tail))
  }
  f(a,b)
}
lcs(List(1,4,2,7,5,8,9,12,6),List(3,5,8,13,15))
