val x = Array(23,1,6,5,10,4,39,3,27,11,7,10,331,18,20)

x.map(_ % 5).sorted

val input = Array(2,15,6,50,41,28,4,19,17,11,9,37,24,1,15)
val numBuck = 5
val min = input.min
val max = input.max
val buckets = Array.fill(numBuck)(List[Int]())
def ins(e:(Int,Int)):Unit={
  val (bIdx,value) = e
  val b = buckets(bIdx)
  def sort(ls:List[Int],x:Int):List[Int]= ls match {
    case Nil => List(x)
    case h::t => if(x>h) h::sort(t,x) else x::ls
  }
  buckets(bIdx) =  sort(b,value)
}
input.map(x=>((x-min)*(numBuck-1)/(max-min),x)).foreach(ins)
buckets.flatten.foreach(println)
