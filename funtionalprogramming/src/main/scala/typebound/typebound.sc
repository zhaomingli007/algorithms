abstract class Animal {
 def name: String
}

abstract class Pet extends Animal {}

class Cat extends Pet {
  override def name: String = "Cat"
}

class Dog extends Pet {
  override def name: String = "Dog"
}

class Lion extends Animal {
  override def name: String = "Lion"
}

//upper type bound
class PetContainer[p<:Pet]  (p:Pet){
  def pet=p
}
val dogC = new PetContainer(new Dog)
val catC = new PetContainer(new Cat)
// val lionC = new PetContainer(new Lion)


//lower type bound

trait Node[+B]{
  def prepend[U>:B](e  :U):Node[U]
}


case class ListNode[+B](h:B,t:Node[B]) extends Node[B]{
  def prepend[U >: B](e:U):ListNode[U] = ListNode(e,this)
  def head = h
  def tail = t 
}

case class Nil[+B]() extends Node[B]{
  def prepend[U >: B](e:U) = ListNode(e,this)
}

trait Bird 
case class AfricanSwallow() extends Bird
case class EuropeanSwallow() extends Bird

val africanSwallowList = ListNode(AfricanSwallow(),Nil())
val birdList:Node[Bird] = africanSwallowList
val birdList2:Node[Bird] = ListNode(EuropeanSwallow(),Nil())

birdList.prepend(EuropeanSwallow())