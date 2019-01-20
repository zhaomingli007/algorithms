import scala.collection._
class Graph(numVertice:Int, isDirected:Boolean=true){

  private[this] val adjacentList = Array.fill(numVertice)(mutable.Map.empty[Int,Double] withDefaultValue Double.PositiveInfinity)

  type EndPoint = (Int,Int)

  private[this] implicit class Edge(points:EndPoint) {
    val (u,v) = points
  }

  //Given an endponit, return a edge cost
  def apply(p:EndPoint) =if(p.u ==p.v) 0.0 else adjacentList(p.u)(p.v)

  def apply(u:Int)(v:Int):Double=this(u->v)

  def has(point:EndPoint) = adjacentList(point.u) contains point.v

  def vertices = adjacentList.indices

  def hasVertice(vs: Int*) = vs forall vertices.contains

  def neighbors(u:Int) = adjacentList(u).keySet

  def update(endpoint:EndPoint,weight:Double) = {
    adjacentList(endpoint.u)(endpoint.v) = weight
    if(!isDirected) adjacentList(endpoint.v)(endpoint.u) = weight
  }

  def -= (point:EndPoint) ={
    adjacentList(point.u) -= point.v
    if(!isDirected) adjacentList(point.v) -= point.u
  } 

  def edges = for(u<- vertices; v<-neighbors(u)) yield (u,v)

  def adjacentMatrix = Array.tabulate(numVertice,numVertice)(this(_,_))

}


//A Star
case class Result[Node](cost:Double,path:Seq[Node])

abstract class AStar[Node]{

  implicit class Updateable[A](ts:mutable.TreeSet[A]){

    def removeFirst = {
      val head = ts.head
      ts -= head
      head
    }

    def updatePriority(n:A, f: A=>Unit) ={
      ts -= n
      f(n)
      ts += n
    }

  }

  def run(start:Node,isGoal: Node => Boolean):Option[Result[Node]]={
    val score =  mutable.Map(start->0d) withDefaultValue Double.NegativeInfinity
    val priority = Ordering by {n:Node=>score(n)+heuristic(n)} reverse
    val queue = mutable.TreeSet(start)(priority)
    val parent = mutable.Map.empty[Node,Node]
    val visited = mutable.Set.empty[Node]

    def rescore(current:Node)(n:Node)
    =score(n)=score(current)+distance(current,n)

    while(queue.nonEmpty){
      val current =  queue.removeFirst
      if(isGoal(current)){
        val trace = mutable.ArrayBuffer.empty[Node]
        var (v,cost) = (current,0d)
        while(parent contains v){
          cost += distance(parent(v),v)
          v +=: trace
          v = parent(v)
        }
        return Some(Result(cost,start +: trace.toSeq))
      }

      neighbors(current) filterNot visited.contains foreach { n =>
        if(score(current)+distance(current,n) >= score(n)){
          queue updatePriority (n,rescore(current))
          parent(n) = current
        }
      }

      visited  += current

    }
    None
  }
 

  def heuristic(n:Node) = 0d
  def distance(from:Node,to:Node) = 1d
  def neighbors(n:Node):Iterable[Node]
}


object Graph{
  def dijkstra(g:Graph,start:Int,goal:Int):Option[Result[Int]] = {
    new AStar[Int]{
      assume(g hasVertice (start,goal))
      override def neighbors(n:Int) = g neighbors n
      override def distance(from:Int,to:Int) = g(from->to)
    } run (start, (_:Int) == goal)
  }
}



val graph = new Graph(6,true)
//Construct graph -> https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#/media/File:Dijkstra_Animation.gif
graph.update((0,1),7)
graph.update((0,2),9)
graph.update((0,5),14)
graph.update((1,2),10)
graph.update((1,3),15)
graph.update((2,5),2)
graph.update((2,3),11)
graph.update((5,4),9)
graph.update((4,3),6)
val rst =Graph.dijkstra(graph,0,3)
// assert(rst.get.cost==29)
rst.get.cost
println(rst.get.path.mkString("->"))
// assert(rst.get.path.mkString("->") == "0->5->4->3")


val rst2 =Graph.dijkstra(graph,0,4)
// assert(rst2.get.cost == 23)
rst2.get.cost
println(rst2.get.path.mkString("->"))




