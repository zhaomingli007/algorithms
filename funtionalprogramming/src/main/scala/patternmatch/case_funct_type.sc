type JBinding = (String,String)
// JBinding => String
// scala.Function1[JBinding,String]
// { case (k,v) => k+":"+v }
trait Function1[-A,+R] {
    def apply(x:A):R
}

//{case (k,v) => k+":"+v } ==
new Function1[JBinding,String]  {
  def apply(x:JBinding) = x match {
    case (k,v) => k+":"+v
  }
}
//Map[Key,Value], Seq[Elem] are functions like
trait Map[Key,Value] extends (Key => Value)
trait Seq[Elem] extends (Int => Elem)

//Partial matches
val f : String => String = {case "ping" => "pong"}
f("ping")
// f("abc") MatchError
//Using partial function
// trait PartialFunction[-A,+R] extends Function1[A,R]{
//   def isDefinedAt(x:A):Boolean
// }


//{case "ping" => "pong"}
new PartialFunction[String,String] {
  def apply(x:String) = x match {
    case "ping" => "pong"
  }
  def isDefinedAt(x:String) = x match{
    case "ping" => true
    case _ => false
  }
}

val f2: PartialFunction[String,String] = {case "ping" => "pong"}

f2("ping")
f2.isDefinedAt("abc")
f2.isDefinedAt("ping")

val f3: PartialFunction[List[Int],String] = {
  case Nil => "one"
  case x :: y :: rest  => "two"
}
f3.isDefinedAt(List(1,2,3))

val f4: PartialFunction[List[Int],String] = {
  case Nil => "one"
  case x :: rest  => rest match {
    case Nil => "two"
  }
}
f4.isDefinedAt(List(1,2,3))
// f4(List(1,2,3)) Match Error












