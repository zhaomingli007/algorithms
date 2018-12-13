//https://danielwestheide.com/
//blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html
// Sequencial executions
import scala.util.Try
type CoffeeBeans = String
type GroundCoffee = String
case class Water(temperature:Int)
type Milk = String
type FrothedMilk = String
type Espresso = String 
type Cappuccino = String 
// def grind(beans:CoffeeBeans):GroundCoffee = s"ground coffee of $beans"
// def heatWater(water:Water):Water = water.copy(temprature = 85)
// def frothMilk(milk:Milk):FrothedMilk = s"frothed $milk"
// def brew(coffee:GroundCoffee,heatWater:Water):Espresso = "espresso"
def combine(esprsso:Espresso,frothedMilk:FrothedMilk):Cappuccino = "cappuccino"
// //Some exceptions
case class GrindingException(msg:String) extends Exception(msg)
case class FrothingException(msg:String) extends Exception(msg)
case class WaterBoilingException(msg:String) extends Exception(msg)
case class BrewingException(msg:String) extends Exception(msg)
// //Going through these steps sequentially
// def prepareCuppuccino():Try[Cappuccino] = for{
//   ground <- Try(grind("arabica beans"))
//   water <- Try(heatWater(Water(25)))
//   espresso <- Try(brew(ground,water))
//   foam <- Try(frothMilk("milk"))
// } yield combine(espresso,foam)
// prepareCuppuccino()


//Working with futures
import scala.concurrent.future
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Random
import scala.util._

def grind(beans:CoffeeBeans):Future[GroundCoffee] = Future {
  println("start grinding")
  Thread.sleep(Random.nextInt(2000).toLong)
  if (beans == "baked beans") throw GrindingException("are you joking?")
  println(s"ground  coffee of $beans")
  s"ground coffee of $beans"
}
def heatWater(water: Water): Future[Water] = Future {
  println("heating the water now")
  Thread.sleep(Random.nextInt(2000).toLong)
  println("hot, it's hot!")
  water.copy(temperature = 85)
}

def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
  println("milk frothing system engaged!")
  Thread.sleep(Random.nextInt(2000).toLong)
  println("shutting down milk frothing system")
  s"frothed $milk"
}

def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
  println("happy brewing :)")
  Thread.sleep(Random.nextInt(2000).toLong)
  println("it's brewed!")
  "espresso"
}

// grind("black beans").onComplete {
//   case Success(goundCoffee) => println(s"got my  coffee: $goundCoffee")
//   case Failure(ex) => println(s"This grinder needs a replacement, seriously! "+ex.getMessage)
// }
//  def temperatureOkey(water:Water):Future[Boolean] = Future{
//    (80 to 85).contains(water.temperature)
//  }

// val flatFuture:Future[Boolean] = heatWater(Water(25)).flatMap{
//   wather => temperatureOkey(wather)
// }

//Still sequencially
def prepareCappuccinoSeq():Future[Cappuccino] = {
  for{
    ground <- grind("arabica beans")
    water <- heatWater(Water(25))
    espresso <- brew(ground,water)
    foam <- frothMilk("milk")
  } yield combine(espresso,foam)
}

// def prepareCappuccino():Future[Cappuccino] ={
//     val groundCoffee = grind("arabica beans")
//     val heatedWater = heatWater(Water(25))
//     val frothedMilk = frothMilk("milk")
//     for{
//       ground <- groundCoffee
//       water <- heatedWater
//       foam <- frothedMilk
//       espresso <- brew(ground,water)
//     } yield combine(espresso,foam)
// }

prepareCappuccinoSeq()

import scala.concurrent.Promise
case class TaxCut(reduction:Int)
val taxcut = Promise[TaxCut]()

val taxcutf = taxcut.future
taxcut.success(TaxCut(100))

def redeemCampaignPledge():Future[TaxCut] = {
  val p = Promise[TaxCut]()
  Future{
      println("Starting the new legislative period.")
      Thread.sleep(2000)
      p.success(TaxCut(20))
      println("We reduced the taxes! You must re-elect us!!!!1111")
  }
  p.future
}
val taxcutF = redeemCampaignPledge()
println("now they're elected")
taxcutF.onComplete{
  case Success(TaxCut(reduction)) => println(s"A mericle,cut taxes by $reduction")
  case Failure(ex) => println(s"broke their promises, caused by ${ex.getMessage}")
}






