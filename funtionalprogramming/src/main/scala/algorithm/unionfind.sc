import scala.collection.generic.Clearable
import scala.collection.mutable

class UnionFind[A] extends PartialFunction[A,A] with Clearable{

  private [this] val parent = mutable.Map.empty[A,A].withDefault(identity)

  private [this] def find(x:A):A = parent(x) match {
    case 'x' => x
    case y   => 
      parent(x) = find(y)
      parent(x)
  }

  

}