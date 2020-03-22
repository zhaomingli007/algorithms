package algorithm.leetcode.rnd1

import org.scalatest.FunSuite

class LetterCombPhoneNumberTest extends FunSuite {

  test("testGetCombinations") {

    val res = LetterCombPhoneNumber.getCombinations("234")
    assertResult(List("adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi", "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi", "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi"))(res)

  }

}
