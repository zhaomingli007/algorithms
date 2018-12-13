import scala.util.Random
val x = Array(23,1,5,10,4,39,3,27,7,10,3,18,20)

x(Random.nextInt(x.length))

def sort(x:List[Int]):List[Int] = x match {
  case x if x.length<=1 => x
  case _  => {
    val pivot = x(Random.nextInt(x.length-1))
    sort(x.filter( pivot >)) ++ x.filter( pivot ==) ++ sort(x.filter( pivot <))
  }
}
sort(x.toList)
