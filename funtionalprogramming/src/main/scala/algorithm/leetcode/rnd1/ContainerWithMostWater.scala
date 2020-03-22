package algorithm.leetcode.rnd1

import scala.annotation.tailrec

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */

object ContainerWithMostWater {
  val points = Array(1, 8, 6, 2, 5, 4, 8, 3, 7)

  def maxArea(points: Array[Int]): Int = {
    var left = 0
    var right = points.length - 1
    var max = 0
    while (left != right) {
      val newMax = Math.min(points(left), points(right)) * (right - left)
      if (newMax > max) max = newMax
      if (points(left) < points(right))
        left = left + 1
      else
        right = right - 1
    }
    max
  }



  /**
   * Stack based solution to maximum rectangle in histogram problem
   * stack always has (h, x) such that h is in increasing order and x is the earliest index at which h can be spanned
   * O(n) - stack can be atmost size of remaining; no recursive step repeats previous; size of remaining never increases
   *
   * @param heights heights of histogram
   * @return area of largest rectangle under histogram
   */
  def maxRectangleInHistogram(heights: Array[Int]): Int = {
    @tailrec def solve(stack: List[(Int, Int)], remaining: List[(Int, Int)],maxArea:Int): Int = {
      def area(x: Int, y: Int) = (heights.length - remaining.length - x) * y
      (stack, remaining) match {
        case (           Nil,          Nil)           => maxArea
        case ((y, x) :: rest,          Nil)           => solve(          rest,          Nil, maxArea max area(x, y))
        case ((y, x) :: rest, (h, i) :: hs) if h <= y => solve(          rest, (h, x) :: hs, maxArea max area(x, y))
        case (             _,  block :: hs)           => solve(block :: stack,           hs, maxArea)
      }
    }
    solve(Nil, heights.toList.zipWithIndex,0)
  }



}

