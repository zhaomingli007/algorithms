package algorithm.leetcode.rnd1

import scala.collection.mutable


object ValidParentheses {

  def isValid(s: String): Boolean = {
    if (s == null) return false
    val stck = mutable.Stack[Char]()
    s.foreach {
      case '{' => stck.push('}')
      case '[' => stck.push(']')
      case '(' => stck.push(')')
      case x if stck.isEmpty ||stck.pop().toInt != x.toInt => return false
      case _ =>
    }
    stck.isEmpty
  }

  def main(args: Array[String]): Unit = {
    ValidParentheses.isValid("{}()")
  }
}
