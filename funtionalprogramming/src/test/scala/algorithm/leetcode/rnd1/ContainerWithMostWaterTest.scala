package algorithm.leetcode.rnd1

import org.scalatest.FunSuite

class ContainerWithMostWaterTest extends FunSuite {

  test("testMaxArea") {
    val res1 = ContainerWithMostWater.maxArea(Array(1, 8, 6, 2, 5, 4, 8, 3, 7))
    println(res1)
    val res2 = ContainerWithMostWater.maxRectangleInHistogram(Array(1, 8, 6, 2, 5, 4, 8, 3, 7))
    println(res2)

    val res3 = ContainerWithMostWater.maxRectangleInHistogram(Array(6, 2, 5, 4, 5, 1, 6 ))
    println(res3)
  }

}
