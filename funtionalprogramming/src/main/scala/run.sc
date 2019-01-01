val a = List("a","b","c","d","e")
val b = List("a","b","c","d","f")

(a zip b).filter(x => x._1 !=x._2)

val itgr = Array(1,2,3,4,5)
itgr.sum

val ar = (1 to 10).toArray


def cry(a:Int)(b:Int) = a+b

cry(1)(2)

val c = cry(1)
c(2)

import scala.collection.mutable.PriorityQueue

case class Donut(name: String, price: Double)
def donutOrder(d: Donut) = d.price
val priorityQueue1: PriorityQueue[Donut] = PriorityQueue(
 Donut("Plain Donut", 1.50),
 Donut("Strawberry Donut", 2.0),
 Donut("Chocolate Donut", 2.50))(Ordering.by(donutOrder))
priorityQueue1.enqueue(Donut("Vanilla Donut", 1.0))
priorityQueue1 += (Donut("Krispy Kreme Donut", 1.0))
println(s"Elements of priorityQueue1 = $priorityQueue1")