package algorithm.leetcode.rnd1

import scala.collection.mutable.ListBuffer


object GenerateParentheses {


  def generateParenthesis(n: Int): List[String] = {

    var parethList = ListBuffer[String]()

    def gen(buff: ListBuffer[String], str: String, open: Int, close: Int, max: Int): Unit = {
      if (str.length() == max * 2) {
        parethList += str;
        return
      }
      if (open < max) {
        gen(buff, str + "(", open + 1, close, max)
      }
      if (close < open) {
        gen(buff, str + ")", open, close + 1, max)
      }

    }

    gen(parethList, "", 0, 0, n)

    parethList.toList

  }


}
