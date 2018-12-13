
import scala.language.implicitConversions
type ODR=Ordering

//Longest continuous increasing sub-sequence
def lcis[A:ODR](s: Seq[A])(implicit odr:ODR[A]) 
= s.tail.scanLeft(List(s.head))((full,c)=> if(odr.lteq(c,full.head)) List(c) else c::full)
.reduceLeft((a,b)=>if(a.length>=b.length) a else b)
lcis(Seq(2,8,1,2,1,2,3,1,4,5,6,4))


//Return length

def lcis2(s: Seq[Int])
= s.tail.scanLeft(List(s.head))((full,c)=> if(c<=full.head) List(c) else c::full)
.reduceLeft((a,b)=>if(a.length>=b.length) a else b)
def findLengthOfLCIS(nums: Array[Int]): Int = {
   if(nums.isEmpty) 0 else lcis2(nums).length
}
findLengthOfLCIS(Array(2,2,2,2,2))


def lis[A: Ordering](s: Seq[A]) = {
   val cache = scala.collection.mutable.Map.empty[Int,Seq[A]]
   def findCandicate(value:Int) = 
}

