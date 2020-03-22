package algorithm.leetcode.rnd1

import org.scalatest.FunSuite

class GenerateParenthesesTest extends FunSuite {

  test("testGenerateParenthesis") {
    val r = GenerateParentheses.generateParenthesis(3).mkString("")
    println(r)
    assertResult("((())))()))())(()))()")(r)
  }

}
