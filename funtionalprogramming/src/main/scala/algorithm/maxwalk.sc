import algorithm.Memo

import scala.language.implicitConversions


def maximumPoints(input :List[List[Int]])  = {
  type DP =  Memo[(Int,Int),List[List[Int]],Int]
  implicit def encode(v:List[List[Int]]):(Int,Int) = (v.length,if(v.length>0) v(0).length else 0)
  lazy val f :DP = Memo {
    case (l::Nil) :: Nil  => l
    case (l::r) :: Nil    => l + f(r::Nil)
    case (l::Nil) :: d    => l + f(d)
    case (c1 :: c2) :: r  => (c1 + f(r)) max (c1 + f(c2 :: r.map(_.tail)))
  }
  f(input)
}

maximumPoints(List(31,  100, 65,  12,  18) 
           :: List(10,  13,  47,  157, 6) 
           :: List(100, 113, 174, 11,  33) 
           :: List(88,  124, 41,  20,  140)
           :: List(99,  32,  111, 41,  20 ) 
           ::Nil)
