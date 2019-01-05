import scala.util.Random

def qs(x:List[Int]):List[Int]= x match {
  case x if x.length<=1 =>x
  case _ =>{
    val pivot = x(Random.nextInt(x.length-1))
    qs(x.filter(pivot>)) ++ x.filter(pivot ==) ++ qs(x.filter(pivot<))
  }
}

qs(Array(23,1,5,10,4,39,3,27,7,10,3,18,20).toList)
