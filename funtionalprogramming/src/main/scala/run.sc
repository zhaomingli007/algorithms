import scala.util.Random

def qs(x:List[Int]):List[Int]= x match {
  case x if x.length<=1 =>x
  case _ =>{
    val pivot = x(Random.nextInt(x.length-1))
    qs(x.filter(pivot>)) ++ x.filter(pivot ==) ++ qs(x.filter(pivot<))
  }
}

qs(Array(23,1,5,10,4,39,3,27,7,10,3,18,20).toList)

def f(a:Int,b:Int)=a*b
Array.tabulate(4,4)(f(_,_))

import scala.util._
val pairs = Array(("a", 5, 1), ("c", 3, 1), ("b", 1, 3))
Sorting.quickSort(pairs)(Ordering.by[(String, Int, Int), Int](_._3))
pairs
val pairs1 = Array(("a", 5, 2), ("c", 3, 1), ("e", 2, 1) ,("b", 1, 3))

Sorting.quickSort(pairs1)(Ordering[(String,Int)].on(x => (x._1,x._3 )))
pairs1

case class Person(name:String, age:Int)
  val people = Array(Person("bob", 3), Person("ann", 32), Person("carl", 49))
  
  // sort by age
  object AgeOrderingDesc extends Ordering[Person] {
    def compare(a:Person, b:Person) = b.age compare a.age  
  }
  Sorting.quickSort(people)(AgeOrderingDesc)
  people

val m2 = scala.collection.mutable.Map("a"->1,"b"->2,"c"->3)
m2 contains "b"

val ab = scala.collection.mutable.ArrayBuffer(10)

ab :+ 1

2 +: ab

3 +=: ab

ab :+ 4

def t3(v:(Int,Int))= v._1*v._2

t3(2->3)
t3((2,3))

def f5(a:Int)(b:Int) = a*b

def f6(c:Int,x:Int=>Int)= x(c)

f6(3,f5(2))




