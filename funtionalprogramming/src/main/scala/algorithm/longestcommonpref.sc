import scala.collection.parallel.immutable.ParSet
import Function.tupled

def commprefix = (x: String, y: String) =>
    x.toStream zip y.toStream takeWhile (Function
      .tupled(_ == _)) map (_._1) mkString

def lcp(x: Array[String]):String =
  x.toList match {
    case head :: Nil       => head
    case one :: two :: Nil => commprefix(one,two)
    case Nil               => ""
    case _                 => {
      val (a,b) = x.splitAt(x.length/2) 
      commprefix(lcp(a),lcp(b))
    }
}
def lcpParRed = {
  val input = ParSet("geeksforgeeks", "geeks", "geek", "geezer")
  val input2 =
    Set("abcd", "abce", "abcxyz", "abdfe", "abcdgfijkl", "abdef", "abcgfij")
  "helloworld".toStream zip "hellohell".toStream takeWhile (Function.tupled(_ == _)) map (_._1) mkString
  //println(input.reduce(commprefix))
  // println(input2.par.reduce(commprefix))
}
