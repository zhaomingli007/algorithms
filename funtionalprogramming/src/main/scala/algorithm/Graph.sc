import scala.collection.mutable.Map


type EndPoints = (Int,Int)
class Graph(numberOfVertices:Int,isDirected:Boolean=true){

  private[this] val adjacentList = Array.fill(numberOfVertices)(Map.empty[Int,Double] withDefaultValue Double.PositiveInfinity)

  private[this] implicit class Edge(points:EndPoints){
    val (u,v) = points
  }

  def apply(points:EndPoints) = if(points.u == points.v) 0.0 else adjacentList(points.u)(points.v)

  def apply(u:Int)(v:Int):Double = this(u->v)

  def has(points:EndPoints):Boolean = adjacentList(points.u) contains points.v 

  def vertices = adjacentList.indices

  def hasVertices(vs:Int*) = vs forall vertices.contains 

  def neighbers(u:Int) = adjacentList(u).keySet

  def update(points: EndPoints, weight: Double) = {
    adjacentList(points.u)(points.v) = weight
    if(!isDirected) adjacentList(points.v)(points.u) = weight
  }

  def -=(points: EndPoints) = {
    adjacentList(points.u) -= points.v
    if(!isDirected) adjacentList(points.v) -= points.u
  }

  def edges = for(u<-vertices; v <- neighbers(u)) yield (u,v)

  def adjacentMatrix = Array.tabulate(numberOfVertices,numberOfVertices)(this(_,_))

}
//Graph algorithms
object Graph {

  /**
  * Dijkstra's shortesst path
  */
  def dijkstra(g:Graph,start:Int,goal:Int) = new AStar[Int] {
    assume(g hasVertices (start, goal))
    override def neighbors(n: Int) = g neighbers n
    override def distance(from: Int, to: Int) = g(from -> to)
  } run (start, _ == goal)

}

