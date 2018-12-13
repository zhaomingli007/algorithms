trait Node
case class TrieNode(end:Boolean,children:Option[Map[Char,TrieNode]]=None) extends Node{

  def insert(str:String)={
    children match {
      case None =>
      case Some(children) =>
    }
  }
}






//Insert 
val root = TrieNode(true)



// def insert(str:String) = {
//   //Find 
//   for(c <- str) yield root insert c 
// }



