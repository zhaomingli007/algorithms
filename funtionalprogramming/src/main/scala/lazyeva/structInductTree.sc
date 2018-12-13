abstract class IntSet{
  def incl(x:Int):IntSet
  def contains(x:Int):Boolean
}


case class NoneEmpty(elem:Int,left:IntSet,right:IntSet) extends IntSet{
  def contains(x:Int):Boolean = 
    if(x<elem) left contains x
    else if(x>elem) right contains x
    else true
  def incl(x:Int):IntSet=
    if(x < elem) NoneEmpty(elem,left incl x, right)
    else if(x > elem) NoneEmpty(elem,left, right incl x)
    else this
}


object Empty extends IntSet{
  def contains(x:Int):Boolean = false 
  def incl(x:Int):IntSet=NoneEmpty(x,Empty,Empty)
}


