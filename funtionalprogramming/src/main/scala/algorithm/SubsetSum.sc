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


def sss(subset:List[Int],sum:Int):Seq[Seq[Int]]={
  type DP = Memo[(Int,Int),(List[Int],Int),Seq[Seq[Int]]]
  implicit def decode(i:(List[Int],Int)):(Int,Int) = (i._1.length,i._2)
  lazy val isChaeivalbe : DP = Memo{
    case(Nil,_) =>Nil
    case(_,0)  => Seq(Nil)
    case(head::l,s) =>  sss(l,s - head).map(_:+head) ++ sss(l,head)
  }
  isChaeivalbe(subset,sum)
}

sss(List(9,8,3,7,5,6),27)
