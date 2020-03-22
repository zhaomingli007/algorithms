package algorithm.leetcode.rnd2

import scala.annotation.tailrec

object MaxRectInHistgram {

  def maxRectangleInHistogram(heights: Array[Int]): Int = {

    @tailrec
    def solve(stack: List[(Int, Int)], remaining: List[(Int, Int)], maxArea: Int): Int = {
      def area(x: Int, y: Int): Int = y * (heights.length - remaining.length - x)
      (stack, remaining) match {
        case (Nil, Nil) => maxArea
        case ((y, x) :: rest, Nil) => solve(rest, Nil, maxArea max area(x, y))
        case ((y, x) :: rest, (h, _) :: hs) if h < y => solve(rest, (h, x) :: hs, maxArea max area(x, y))
        case (_, blk :: hs) => solve(blk :: stack, hs, maxArea)
      }
    }

    solve(Nil, heights.toList.zipWithIndex, 0)

  }

}
