//Immutable collections: List,Vector,Stream,Range,String,Map,Set
//Mutable collection: Array
val fruit = "apple"::("banana"::("orange"::Nil))
fruit.tail
fruit.last
fruit.init
val fruit2 = "apple"::"banana"::"orangle"::"pear"::Nil
fruit zip fruit2
fruit2.reverse
val fruit3 = fruit2.updated(2,"blueberry")
fruit2.partition(_.startsWith("a"))
//concatenation
fruit ++ fruit2
val r = 1 until 6
val r2 = 1 to 6
//Reduce
val num = 8.0::2.0::3.0::4.0::5.0::6.0::3.0::Nil
num.reduceLeft(_/_)//(1+2)+3)+4)+5
num.reduceRight(_/_)//1+(2+(3+(4+5)))
num.foldLeft(1.0)(_/_)
num.foldRight(1.0)(_/_)
num.exists(_ == null)
num.forall(_>1)
num.sum
num.product
num.distinct
10 +: num
num :+ 20

val myMap = Map("I" -> 1, "V" -> 5, "X" -> 10) 
myMap get "Y"
myMap.updated("V",16)
val xs = Stream(1,2,3)
(1 until 10).toStream
100 #:: xs
Stream.cons(11,xs)

import scala.util.Sorting
import scala.math.Ordering
val pairs = Array(("a", 5, 2), ("c", 3, 1), ("b", 1, 3))
// sort by 2nd element
Sorting.quickSort(pairs)(Ordering.by[(String, Int, Int), Int](_._2))
pairs
// sort by the 3rd element, then 1st
Sorting.quickSort(pairs)(Ordering[(Int, String)].on(x => (x._3, x._1)))
pairs
