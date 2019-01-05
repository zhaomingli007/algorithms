abstract class Animal{
  def name:String
}

case class Cat(name:String) extends Animal
case class Dog(name:String) extends Animal

//Convariance

object ConvarianceTest extends App{
  def printAnimalNames(animals:List[Animal])= animals.foreach(a=>println(a.name))
  val cats = List(Cat("cat1"),Cat("cat2"),Cat("cat3"))
  val dogs = List(Dog("dog1"),Dog("dog2"),Dog("dog3"),Dog("dog4"))
  printAnimalNames(cats)
  printAnimalNames(dogs)
}

ConvarianceTest


//Contravariance
abstract class Printer[-A]{
  def print(value:A):Unit
}

class AnimalPrinter extends Printer[Animal]{
  def print(animal:Animal)=println(s"The animal's name is $animal.name")
}

class CatPrinter extends Printer[Cat]{
  def print(cat:Cat)=println(s"The cat's name is $cat.name")
}

object ContravarianceTest extends App{
  val tomCat = Cat("tom cat")
  def printTomCat(printer:Printer[Cat])=printer.print(tomCat)

  val catPrinter = new CatPrinter
  val aniPrinter = new AnimalPrinter

  printTomCat(catPrinter)
  printTomCat(aniPrinter)
}
ContravarianceTest

//Invariance
class Container[A](value:A){
  private var _value:A=value
  def getValue:A=_value
  def setValue(value:A)=_value=value
}

val catCont = new Container(Cat("my cat"))
//Compiler error from below lines
// val animalContainer:Container[Animal] = catCont
// val animalContainer.setValue(Dog("my dog"))
// val cat = catCont.getValue

//Other example
trait Function[-T,+R]

abstract class SmallAnimal extends Animal 
case class Mouse(name:String) extends SmallAnimal

class EatRel[-T,+R](f:T=>R) extends (T=>R){

  def apply(in:T)=f(in)
}



def eat(a:Cat)=Mouse(s"me mouse 2 was ate by ${a.name}")
def eat2(a2:Animal)=Mouse(s"me mouse 3 by ${a2.name}")

val e1 = new EatRel[Cat,SmallAnimal](eat)

e1(Cat("big cat1"))

val e2 = new EatRel[Cat,SmallAnimal](eat2)
e2(Cat("small cat1"))