package algorithm.leetcode.rnd1

object ThreeSum {

  def threeSum(unsortNums: Array[Int]): List[List[Int]] = {
    val nums = unsortNums.sorted
    var res = List[List[Int]]()
    for (i <- 0 to nums.length - 2) {
      if (i == 0 || nums(i) != nums(i - 1)) {
        var lo = i + 1
        var hi = nums.length - 1
        val sum = 0 - nums(i)
        while (lo < hi) {
          if (nums(lo) + nums(hi) == sum) {
            res = List(nums(i), nums(lo), nums(hi))::res
            while (lo < hi && nums(lo) == nums(lo + 1)) lo = lo + 1
            while (lo < hi && nums(hi) == nums(hi - 1)) hi = hi - 1
            lo = lo + 1
            hi = hi - 1
          } else if (nums(lo) + nums(hi) < sum) lo = lo + 1
          else hi = hi - 1
        }
      }
    }
    res
  }


}
