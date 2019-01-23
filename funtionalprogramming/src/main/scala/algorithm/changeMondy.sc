//https://leetcode.com/problems/coin-change-2/ (greedy?)
import scala.language.implicitConversions
import Memo._

def coinchange(s: List[Int], t: Int) = {
  type DP = Memo[ (Int, Int), (List[Int], Int),Seq[Seq[Int]]]
  implicit def encode(key: (List[Int], Int)):(Int,Int) = (key._1.length, key._2)

  lazy val f: DP = Memo {
    case (Nil, 0) => Seq(Nil)
    case (Nil, _) => Nil
    case (_, x) if x< 0  => Nil
    case (a :: as, x) => f(a::as, x - a).map(_ :+ a) ++ f(as, x)
  }

  f(s, t)
}

val cm = coinchange(List(5,10,17,9,8,3,7,6),27)
cm.length
