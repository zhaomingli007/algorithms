

//Partion function
trait Coin
case class Gold() extends Coin
case class Silver() extends Coin
//If we are only interested in Gold
val pf:PartialFunction[Coin,String] = {
  case Gold() => "Yeah gold！"
}
pf.isDefinedAt(Gold())
pf.isDefinedAt(Silver())
pf(Gold())
pf(Silver())















































