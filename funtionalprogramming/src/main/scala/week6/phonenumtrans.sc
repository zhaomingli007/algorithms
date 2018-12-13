import scala.io.Source


  val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")
  val words = in.getLines.toList filter (_ forall (c => c.isLetter))
  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
    '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ"
  )
  //  var charCode: Map[Char, Char] = mnem flatMap (x => for (c <- x._2) yield c -> x._1)
  val charCode: Map[Char, Char] = for ((k, v) <- mnem; c <- v) yield (c, k)

  //  def wordCode(word: String): String = for (c <- word) yield charCode(c)
  def wordCode(word: String): String = word.toUpperCase() map charCode //Map take a key as parameter and return value
  wordCode("Java")
  
  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq()

  def encode(number: String): Set[List[String]] =
    if (number.isEmpty) Set(List())
    else {
      for {
        split <- 1 to number.length
        word <- wordsForNum(number take split)
        rest <- encode(number drop split)
      } yield word :: rest

    }.toSet

  encode("772362362")

  def translate(number:String) :Set[String]=
    encode(number) map (_ mkString " ")

  translate("772362362")
