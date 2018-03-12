package algorithm.stfd

/**
  * Created by zhao on 2/7/18.
  */
object QuickSort {

  def main(args: Array[String]): Unit = {
    val input = Array(1, 3, 5, 7, 9, 2, 4, 6, 8, 10)
    val sortedInput = sort(input, 0, input.length - 1)
    sortedInput.foreach(x => print(s"$x,"))
  }

  def partition(input: Array[Int], l: Int, r: Int): (Array[Int], Int) = {
    var i = l + 1
    val pivot = input(l)
    for (j <- l + 1 to r) {
      if (input(j) < pivot) {
        val t = input(j)
        input(j) = input(i)
        input(i) = t
        i += 1
      }
    }
    val t = input(i - 1)
    input(i - 1) = pivot
    input(l) = t
    (input, i - 1)
  }

  def sort(input: Array[Int], l: Int, r: Int): Array[Int] = {
    if (l >= r) return input
    val pivotIdx = choosePivot(input, l, r)
    //Put pivot as first element in array
    val t = input(l)
    input(l) = input(pivotIdx)
    input(pivotIdx) = t
    val part = partition(input, l, r)
    sort(part._1, l, part._2 - 1)
    sort(part._1, part._2 + 1, r)


  }

  def choosePivot(input: Array[Int], i: Int, r: Int): Int = i

}