package algorithm.leetcode.rnd1

import org.scalatest.FunSuite

class ThreeSumTest extends FunSuite {

  test("testThreeSum") {

    val r1 = ThreeSum.threeSum(Array(-1, 0, 1, 2, -1, -4))
    println(r1)
    assertResult(List(List(-1, 0, 1),List(-1, -1, 2)))(r1)

    val r2 = ThreeSum.threeSum(Array(0, -1, 2, -3, 1))
    assertResult(List(List(-1, 0, 1),List(-3, 1, 2)))(r2)

    val r3 = ThreeSum.threeSum(Array(-1,0,1,2,-1,-4))
    assertResult(List(List(-1, 0, 1),List(-1, -1, 2)))(r3)

  }

}
