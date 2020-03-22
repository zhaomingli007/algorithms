package algorithm.leetcode.rnd1

import org.scalatest.FunSuite

class ValidParenthesesTest extends FunSuite {

  test("testIsValid") {

    val r1 = ValidParentheses.isValid("{}")
    assertResult(true)(r1)
    val r2 = ValidParentheses.isValid("")
    assertResult(true)(r2)
    val r3 = ValidParentheses.isValid("{")
    assertResult(false)(r3)
    val r4 = ValidParentheses.isValid(null)
    assertResult(false)(r4)
    val r5 = ValidParentheses.isValid("()[]{}")
    assertResult(true)(r5)
    val r6 = ValidParentheses.isValid("{[]}")
    assertResult(true)(r6)
    val r7 = ValidParentheses.isValid("([)]")
    assertResult(false)(r7)

  }

}
