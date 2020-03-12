/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
val points = Array(1,8,6,2,5,4,8,3,7)

def maxArea(points: Array[Int]): Int = {
  var left = 0
  var right = points.length-1
  var max = 0
  while(left != right){
    val newMax = Math.min(points(left),points(right))*(right - left)
    if(newMax> max ) max = newMax
    if(points(left) < points(right))
      left = left + 1
    else
      right = right - 1
  }
  max
}
val max = maxArea(points)
println(max)