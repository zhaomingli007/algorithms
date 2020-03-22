import scala.language.implicitConversions
//Use memo
import Memo._
implicit class RichList[A](l: List[A]) {
  implicit class RichList[A](l: List[A]) {
    def -(elem: A): List[A] = l match {
      case Nil => Nil
      case x :: xs if x == elem => xs
      case x :: xs => x :: (xs - elem)
    }
  }
}

/**
* O(s.map(abs).sum * s.length)
*/
def ss(subset:List[Int],sum:Int):Boolean={
  type DP = Memo[(Int,Int),(List[Int],Int),Boolean]
  implicit def decode(i:(List[Int],Int)):(Int,Int) = (i._1.length,i._2)

  lazy val isChaeivalbe : DP = Memo{
    case(Nil,_) =>false
    case(_,0)  => true
    case(head::l,s) => ss(l,s-head) || ss(l,s)
  }
  isChaeivalbe(subset,sum)
}

ss(List(9,8,3,7,5,6),6)


  def subsetSum(s: List[Int], t: Int) = {
    type DP = Memo[ (Int, Int), (List[Int], Int),Seq[Seq[Int]]]
    implicit def encode(key: (List[Int], Int)):(Int,Int) = (key._1.length, key._2)

    lazy val f: DP = Memo {
      case (Nil, 0) => Seq(Seq())
      case (Nil, _) => Nil
      case (a :: as, x) => f(as, x - a).map(_ :+ a) ++ f(as, x)
    }

    f(s, t)
  }

subsetSum(List(5,1,-4,-10,9,-1,-4,-5,-8,3,1,4,2,-8,-4,3,-4,-5,1,7,8,6,2,8),0).filter(_.size == 3).map(_.sorted).distinct
