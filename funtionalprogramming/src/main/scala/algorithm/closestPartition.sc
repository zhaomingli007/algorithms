
import scala.language.implicitConversions
import scala.math.Ordering.Implicits._
import Memo._

def main(args:Array[String])={
  type DP = Memo[(Int, Int),(List[Int], Int), Option[Seq[Int]]]
  implicit def encode(key: (List[Int], Int)):(Int,Int) = (key._1.length, key._2)
  implicit class IntExt(x:Int) {
    def -->(y:Int) = x to y by (if (x<y ) 1 else -1)
  }
  implicit class TraversableExt[A](x:Traversable[A]) {
      def firstDefined[B](f: A=>Option[B]):Option[B] = x collectFirst Function.unlift(f)
  }

  lazy val f: DP = Memo {
    case (_, 0)   => Some(Nil)
    case (Nil, _) => None
    case (a :: as, x) => f(as, x - a).map(_ :+ a) orElse f(as, x)
  }

  val s = List(1,2,3,4,5,6,7,8,9)

  val possible = f(s, _: Int) 

  val ss = (s.sum/2 --> 0 firstDefined possible).get   // find largest such x < s.sum/2 (always a solution at 0)

  ss.foreach(println)


  // implicit class TraversableExtension[A](t: Traversable[A]) {
  //   def firstDefined[B](f: A => Option[B]): Option[B] = t collectFirst Function.unlift(f)
  // }

  // (lx.sum/2 --> 0 firstDefined possible).get

  // val ff: PartialFunction[Int,Int]  = { case i if i % 2 ==0 => i * 2 }
  // ff.lift
  // (5 --> 0 collectFirst ff )
}

main(Array())

 List(1,2,3,4,5,6,7,8,9).sum/2