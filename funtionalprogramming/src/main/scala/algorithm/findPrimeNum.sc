import scala.language.implicitConversions
List(2 until 10:_*)
val arr = Array("What's", "up", "doc?")
def sum(i:Int*)=i.sum
sum(Array(1,2,3,4):_*)

import scala.collection.mutable.BitSet

val n = 2 to 10
val isPrime = BitSet(n: _*)
isPrime(3)
isPrime --= List(2,3)
List(2*2 to 10 by 2:_*)

def getPrime(n:Int)={
  val ns = 2 to n
  val s = BitSet(ns:_*)
  for(x<- ns takeWhile (i=>i*i<=n) if(s(x))){
    s--=x*x to n by x
  }
  s
}

getPrime(10)