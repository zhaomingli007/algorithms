val s = List("abc","abcd","abc","abcaa","abcdefg")
def commprefix = (x: String, y: String) =>
    x.toStream zip y.toStream takeWhile (Function
      .tupled(_ == _)) map (_._1) mkString

//log(M Log N)
def lcp(x: Seq[String]):String =
  x.toList match {
    case head :: Nil       => head
    case one :: two :: Nil => commprefix(one,two)
    case Nil               => ""
    case _                 => {
      val (a,b) = x.splitAt(x.length/2) 
      commprefix(lcp(a),lcp(b))
    }
  }
lcp(s)  
lcp(List())
lcp(List("abcd"))
lcp(List("abcd",""))
lcp(List("abcd","abcdefg"))
lcp(List("abc","abcdefg","abcdefgh"))
