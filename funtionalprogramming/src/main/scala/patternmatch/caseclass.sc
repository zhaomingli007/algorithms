abstract class JSON

case class JString(value:String) extends JSON
case class JInteger(value:Integer) extends JSON
case class JBoolean(value:Boolean) extends JSON
case class JDouble(value:Double) extends JSON
case object JNil extends JSON
case class JObject(value:Map[String,JSON]) extends JSON
case class JSeq(value:Seq[JSON]) extends JSON

val j = JObject(
        Map("firstName" -> JString("John"),
            "lastName" -> JString("Smith"),
            "isAlive" -> JBoolean(true),
            "age" -> JInteger(27),
            "address" -> JObject{
              Map("streetAddress"->JString("21 2nd Street"),
                  "city" -> JString("New York")
              )
            },
            "phoneNumbers" -> JSeq(
              Seq(
                JObject(
                  Map("type" -> JString("home"),
                      "number" -> JString("212 555-1234")
                  )
                ),
                JObject(
                  Map("type" -> JString("office"),
                      "number" -> JString("646 555-4567")
                  )
                )
              )
            ),
            "children" -> JSeq(Seq()),
            "spouse" -> JNil
        )
)

def getJsonStr(m:JSON):String= m match {
  case JObject(obj) =>
    val kv = obj map {
      case (k,v) =>  "\""+k+"\":" + getJsonStr(v) 
    } 
    "{" + (kv mkString ",") +"}"
  case JString(str) => "\""+str+"\""
  case JInteger(intv) => intv.toString
  case JBoolean(b) => b.toString
  case JSeq(seq) =>  "[" +  (seq map getJsonStr mkString ",") + "]"
  case JNil => "null"
}

val s = getJsonStr(j)
println(s)

