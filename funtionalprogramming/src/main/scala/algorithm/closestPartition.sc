def f(a:List[Int],b: =>Array[Int])={
  b(1)=6
  println(a.sum+b.sum)
}

val s = List(1,2,3)
val y = Array(4,5)
f(s,y)
y

import Memo._

def closestPartition(s: List[Int]) = {
  type DP = Memo[(List[Int], Int), (Int, Int), Option[Seq[Int]]]
  implicit def encode(key: (List[Int], Int)):(Int,Int) = (key._1.length, key._2)

  lazy val f: DP = Memo {
    case (_, 0) => Some(Nil)
    case (Nil, _) => None
    case (a :: as, x) => f(as, x - a).map(_ :+ a) orElse f(as, x)
  }

  val possible = f(s, _: Int)                 // check if _ can be created using all elements of s
  (s.sum/2 --> 0 firstDefined possible).get   // find largest such x < s.sum/2 (always a solution at 0)
}