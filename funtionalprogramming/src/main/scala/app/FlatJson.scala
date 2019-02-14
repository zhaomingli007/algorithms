
import play.api.libs.json._

import scala.util.{Failure, Try}

object FlatJson {


  /**
    *
    * @param jstr the json string to be flattened.
    * @return the flattened string if success or fail if exception occurred.
    */
  def flatten(jstr: String) = {

    jstr match {
      case v if jstr != null && jstr.trim != "" => parse(v)
      case _ => Failure(new Exception("empty input"))
    }
  }

  private[this] def parse(s: String) = {

    def rec(root: JsValue, prefix: Option[String]): String = {
      root match {
        case JsObject(obj) => {
          val kv = obj map {
            case (k, v) => {
              val prfix = prefix match {
                case Some(p) => p + "." + k
                case None => k
              }
              val fv = rec(v, Some(prfix))
              v match {
                case JsObject(_) => fv
                case _ => s""""$prfix":$fv"""
              }
            }
          }
          s"${kv.mkString(",")}"
        }
        case JsString(str) => "\"" + str + "\""
        case JsNumber(intv) => intv.toString
        case JsBoolean(b) => b.toString
        case JsArray(seq) => "[" + (if (seq.isEmpty) "" else seq.tail.foldLeft(seq.head.toString)(_ + "," + _)) + "]"
        case JsNull => "null"
      }
    }

    Try {
      val r = Json.parse(s)
      r match {
        case JsObject(_) => s"{${rec(r, None)}}"
        case JsArray(a)  => "["+a.map(x=>s"{${rec(x, None)}}").mkString(",")+"]"
        case _           => rec(r, None)
      }
    }

  }


}
