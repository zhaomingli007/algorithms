package algorithm.leetcode.rnd1

import org.scalatest.FunSuite

class RegularExprMatchingTest extends FunSuite {

  test("testIsMatch") {
    val t = RegularExprMatching.isMatch("abbbcefg","""a.*cefg""")
    println(t)
  }

}
