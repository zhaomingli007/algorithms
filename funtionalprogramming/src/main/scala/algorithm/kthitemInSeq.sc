import scala.util.Random
import scala.math.Ordering.Implicits._
import scala.language.implicitConversions
//Randomized algorithm
//Kth largest/smallest (with _ > pivot / _ < pivot )
def quickselect[A:Ordering](s:Array[A],k1:Int):A= {
    val k = k1-1
    val pivot = s(Random.nextInt(s.length))
    val (low, rest) = s.partition(_ > pivot)
    if (k < low.size) {
      quickselect(low, k1)
    } else {
      val (equal, high) = rest.partition(_ == pivot)
      if (k < low.size + equal.size) pivot else quickselect(high, k1- low.size - equal.size)
    }
}

quickselect(Array(3,2,1,5,6,4),2)