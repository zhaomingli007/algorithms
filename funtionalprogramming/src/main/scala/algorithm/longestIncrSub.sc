
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


import scala.math.Ordering.Implicits._
def lis(s:Array[Int]):Array[Int] = {

  //cache
  val cache = scala.collection.mutable.Map(0->Array.empty[Int])
  def longest = cache.size - 1
  //Find position to be updated
  def findIdx(x:Int,start:Int=0,end:Int=longest):Int={
    if(start == end) start 
    else {
      val mid = (start+end+1)/2

      if(cache(mid).last>x) findIdx(x,start,mid-1) else findIdx(x,mid,end)
    }
  }
  //Update cache
  s.foreach{
    c=>
    val p = if(cache.size>1 && c>cache(longest).last)longest else  findIdx(c)  
    cache(p+1) = cache(p):+c
  }

  cache(longest)
}

lis(Array(0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15))
