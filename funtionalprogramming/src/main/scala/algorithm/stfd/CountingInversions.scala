package algorithm.stfd

/**
  * Created by zhao on 1/25/18.
  */
object CountingInversions {

  def main(args: Array[String]): Unit = {
    val input = Array(11, 2, 1, 4, 5, 3, 10, 6, 9, 7, 8, 12)
    val r = countInversions(input, 0, input.length - 1)
    println(s"${r._1.mkString(",")}, ${r._2}")

    val input2 = Array(1, 3, 5, 7, 9, 2, 4, 6, 8, 10)
    val r2 = countInversions(input2, 0, input2.length - 1)
    println(s"${r2._1.mkString(",")}, ${r2._2}")
  }

  def countInversions(input: Array[Int], idxStart: Int, idxEnd: Int): (Array[Int], Int) = {
    if (idxStart == idxEnd) return (Array(input(idxStart)), 0)
    val mid = idxStart + (idxEnd - idxStart) / 2
    val left = countInversions(input, idxStart, mid)
    val right = countInversions(input, mid + 1, idxEnd)
    val split = mergeCountSplitInversions(left._1, right._1)
    (split._1, left._2 + right._2 + split._2)
  }

  def mergeCountSplitInversions(left: Array[Int], right: Array[Int]): (Array[Int], Int) = {
    var i = 0
    var j = 0
    var k = 0
    val merged = new Array[Int](left.length + right.length)
    var splitInvs = 0
    (0 to merged.length - 1).foreach {
      idx =>
        merged(k) = if (j == right.length || (i != left.length && left(i) < right(j))) {
          i += 1
          left(i - 1)
        } else if (i == left.length || (j != right.length && left(i) >= right(j))) {
          j += 1
          splitInvs += (left.length - i)
          right(j - 1)
        } else 0 //This case should NEVER happen
        k += 1
    }
    (merged, splitInvs)
  }
}
