package algorithm.stfd

class MergeSort {

  /**
    * Sort an array of tuple of Int
    *
    * @param input    Array of tuple to be sorted.
    * @param idxStart Array index start from
    * @param idxEnd   Array index end with
    * @param sortBy   1 sorted by 1st element in tuple and 2 by 2nd.
    * @return Sorted array
    */
  def mergeSort(input: Array[(Int, Int)], idxStart: Int, idxEnd: Int, sortBy: Int): Array[(Int, Int)] = {
    if (idxStart == idxEnd) return Array(input(idxStart))

    val mid = idxStart + (idxEnd - idxStart) / 2
    val left = mergeSort(input, idxStart, mid, sortBy)
    val right = mergeSort(input, mid + 1, idxEnd, sortBy)

    val size = left.length + right.length
    val merged = new Array[(Int, Int)](size)
    var i = 0
    var j = 0
    var k = 0
    (0 to size - 1).foreach {
      x =>
        merged(k) = if (j == right.length || (i != left.length && compare(sortBy, left, right, i, j, lt))) {
          i += 1
          left(i - 1)
        } else if (i == left.length || (j != right.length && compare(sortBy, left, right, i, j, get))) {
          j += 1
          right(j - 1)
        } else (0, 0) //This case should NEVER happen
        k += 1
    }
    merged
  }


  def lt = (a: Int, b: Int) => a < b

  def get = (a: Int, b: Int) => a >= b


  def compare(sortBy: Int, left: Array[(Int, Int)], right: Array[(Int, Int)],
              i: Int, j: Int, op: (Int, Int) => Boolean) = {
    op((if (sortBy == 1) left(i)._1 else left(i)._2), (if (sortBy == 1) right(j)._1 else right(j)._2))
  }
}

object MergeSort {
  def main(args: Array[String]): Unit = {
    val sort = new MergeSort
    val input = Array(11, 2, 1, 4, 5, 3, 10, 6, 9, 7, 8, 12).map((_, 0))
    val result = sort.mergeSort(input, 0, input.length - 1, 1)
    result.foreach(x => print(s"${x._1} "))
  }

  def sort(input: Array[(Int, Int)], sortBy: Int = 1): Array[(Int, Int)] = {
    val sort = new MergeSort
    sort.mergeSort(input, 0, input.length - 1, sortBy)
  }

}
