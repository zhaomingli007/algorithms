package algorithm.leetcode.rnd1

import util.control.Breaks._

/**
 * Solution:
 * Step1, from right to left, find the first number which not increase in a ascending order. In this case which is 3.
 * Step2, here we can have two situations:
 *
 * We cannot find the number, all the numbers increasing in a ascending order. This means this permutation is the last permutation, we need to rotate back to the first permutation. So we reverse the whole array, for example, 6,5,4,3,2,1 we turn it to 1,2,3,4,5,6.
 *
 * We can find the number, then the next step, we will start from right most to leftward, try to find the first number which is larger than 3, in this case it is 4.
 * Then we swap 3 and 4, the list turn to 2,4,6,5,3,1.
 * Last, we reverse numbers on the right of 4, we finally get 2,4,1,3,5,6.
 *
 * Time complexity is: O(3*n)=O(n).
 */
object NextPermutation {


  def nextPermutation(nums: Array[Int]) :Unit = {
    val n = nums.length - 1
    var p = -1
    var pv = 0

    breakable {
      for (i <- n - 1 to 0 by -1) {
        if (nums(i) < nums(i + 1)) {
          p = i
          pv = nums(i)
          break
        }
      }
    }


    if (p == -1) {
      reverse(nums, 0, n)
      return
    }

    breakable {
      for (i <- n to 0 by -1) {
        if (nums(i) > pv) {
          val t = nums(i)
          nums(i) = pv
          nums(p) = t
          break
        }
      }
    }
    reverse(nums, p + 1, n)
  }

  def reverse(temp: Array[Int], start: Int, end: Int) = {
    var s = start
    var e = end
    while (s < e) {
      val t = temp(s)
      temp(s) = temp(e)
      temp(e) = t
      s += 1
      e -= 1
    }
  }

}
