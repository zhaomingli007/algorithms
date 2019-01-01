package algorithm
import scala.collection.mutable

case class Result[Node](cost:Double,path:Seq[Node])

abstract class AStar[Node]{

  implicit class Updateable[A](ts:mutable.TreeSet[A]){

    def removeFirst = {
      val head = ts.head
      ts -= head
      head
    }

    def updatePriority(n:A,f:A=>Unit)={
      ts -= n
      f(n)
      ts += n
    }
  }

  def run(start:Node,isGoal: Node => Boolean):Option[Result[Node]] ={
    val score = mutable.Map(start->0d) withDefaultValue Double.PositiveInfinity
    val priority = Ordering by {n:Node=>score(n)+heuristic(n)}
    val queue = mutable.TreeSet(start)(priority)
    val parent = mutable.Map.empty[Node,Node]
    val visited = mutable.Set.empty[Node]

    def reScore(current:Node)(n:Node)={
      score(n)=score(current) + distance(current,n)
    }

    while(queue.nonEmpty){
      val current = queue.removeFirst
      if(isGoal(current)){
        val trace = mutable.ArrayBuffer.empty[Node]
        var (v,cost) = (current,0d)
        while(parent contains v ){
          cost += distance(parent(v),v)
          v +=: trace
          v = parent(v)
        }
        return Some(Result(cost,start +: trace.toSeq))
      }

      neighbors(current) filterNot visited.contains foreach { n => 
        if(score(n) >= score(current) + distance(current,n)){
          queue updatePriority (n,reScore(current))
          parent(n) = current
        }
      }
      visited += current
    }
    None
  }

  def neighbors(n:Node):Iterable[Node]
  def distance(from:Node,to:Node) = 1d
  def heuristic(n:Node) = 0d
}