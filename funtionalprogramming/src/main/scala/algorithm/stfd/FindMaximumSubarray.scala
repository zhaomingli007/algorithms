package algorithm.stfd

/**
  * Created by zhao on 1/25/18.
  */
object FindMaximumSubarray {
  def main(args: Array[String]): Unit = {
    val result = findMaximum(Array(10, 2, -8, 7, -10, 20, 3, -4, 16, -4), 0, 9)
    println(result)
  }

  /**
    * @param input , input array
    * @param start , the start index of maximum subarray
    * @param end   , the end index of maximum subarray
    * @return triple with array indexes of start and end of maximum subarray.
    */
  def findMaximum(input: Array[Int], start: Int, end: Int): (Int, Int, Int) = {
    if (start == end) return (start, end, input(start))
    val mid = start + (end - start) / 2
    //High half
    val high = findMaximum(input, start, mid)
    //How half
    val low = findMaximum(input, mid + 1, end)
    //Across middle
    val across = findMaximumAcrossArray(input, mid, start, end)
    val max = List(high, low, across).reduceLeft((x, y) => if (x._3 > y._3) x else y)
    max
  }

  def findMaximumAcrossArray(input: Array[Int], mid: Int, start: Int, end: Int): (Int, Int, Int) = {
    var idxMin = (mid, input(mid))
    var idxMax = (mid + 1, input(mid + 1))

    (mid to start by -1).foldLeft(0) {
      (v, i) =>
        val foldV = v + input(i)
        if (foldV > idxMin._2) idxMin = (i, foldV)
        foldV
    }

    (mid + 1 to end).foldLeft(0) {
      (v, i) =>
        val foldV = v + input(i)
        if (foldV > idxMax._2) idxMax = (i, foldV)
        foldV
    }
    (idxMin._1, idxMax._1, idxMin._2 + idxMax._2)
  }


}