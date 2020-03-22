package algorithm.leetcode.rnd2

import algorithm.leetcode.rnd1.ContainerWithMostWater
import org.scalatest.FunSuite

class MaxRectInHistgramTest extends FunSuite {

  test("testMaxRectangleInHistogram") {
    val res3 = MaxRectInHistgram.maxRectangleInHistogram(Array(6, 2, 5, 4, 5, 1, 6))
    println(res3)
    assertResult(12)(res3)

    val res2 = ContainerWithMostWater.maxRectangleInHistogram(Array(1, 8, 6, 2, 5, 4, 8, 3, 7))
    println(res2)
    assertResult(16)(res2)
  }

}
