import algorithm.Memo

import scala.language.implicitConversions
//Use memo
case class Item(weight:Int,value:Int){
  val ratio = value*1.0f/weight
}

//DynamicProgramming (0,1 Knapsack) (Partition Equal Subset Sum)
def dpknapsack(items:List[Item],capacity:Int) = {
  type DP = Memo[(Int, Int),(List[Item], Int),  (Int, List[Item])]
  implicit def encode(key: (List[Item], Int)):(Int,Int) = (key._1.length, key._2)
  lazy val f :DP = Memo {
    case (i :: t,tweight) if i.weight<=tweight =>
      val ((v1,it1),(v2,it2)) = (f(t,tweight),f(t,tweight-i.weight))
      if(v2+i.value>v1) (v2+i.value,i::it2) else (v1,it1)
    case (i :: t,tweight) if tweight>0 => f(t,tweight)
    case _ => (0,Nil)
  }
  f(items,capacity)
}
dpknapsack(List(Item(10,20),Item(11,2),Item(2,5),Item(8,12),Item(12,15),Item(20,30)),60)



//fractional Knapsack
import scala.util.Random

case class Knapsack(capacity:Int,items:List[Item]=Nil,cost:Float=0)

def greedy(its:List[Item], capacity:Int)={
  //step 1: calculate ratio value/weight for each item.

  def sort(x:List[Item]):List[Item] = x match {
    case x if x.length<=1 => x
    case _  => {
      val pivot = x(Random.nextInt(x.length-1))
      sort(x.filter( pivot.ratio < _.ratio)) ++ x.filter( pivot ==) ++ sort(x.filter( pivot.ratio > _.ratio))
    }
  }
  //step 2 sort by decreasing order
  val sorted = sort(its)
  //step 3 putting the item into the Knapsack
  val knapsack = sorted.foldLeft(Knapsack(capacity)){
    (ksack,c)=>
    if(ksack.capacity>=c.weight) Knapsack(ksack.capacity-c.weight,c::ksack.items,ksack.cost+c.value)
    else  Knapsack(capacity,c::ksack.items,(capacity-c.weight)*c.value*1.0f/c.weight+ksack.cost)
  }
  knapsack.cost
}

greedy(List(Item(10,5),Item(20,16),Item(3,3),Item(20,18),Item(5,6),Item(2,8),Item(9,12)),50)
greedy(List(Item(10,60),Item(20,100),Item(30,120)),50)
