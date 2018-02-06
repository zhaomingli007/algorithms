package algorithm.stfd

import scala.math._
import scala.util.Random
/**
  * Created by zhao on 1/29/18.
  */

object FindClosestPair {
  type Point = (Int, Int)

  def main(args: Array[String]): Unit = {
    val input = generatePoints()
    //Make two copies sorted by co-ordinate x and y respectively.
    val inputPx = MergeSort.sort(input, 1)
    val inputPy = MergeSort.sort(input, 2)
    val closestPair = findClosetPair(inputPx, inputPy, 0, inputPx.length - 1)
    println(closestPair)


    //Test case 2
    val input2 = Array((0, 0), (0, 1), (100, 45), (2, 3), (9, 9))
    val inputPx2 = MergeSort.sort(input2, 1)
    val inputPy2 = MergeSort.sort(input2, 2)
    val closestPair2 = findClosetPair(inputPx2, inputPy2, 0, inputPx2.length - 1)
    println(closestPair2)

    //Test case 3
    val input3 = Array((0, 0), (-4, 1), (-7, -2), (4, 5), (1, 1))
    val inputPx3 = MergeSort.sort(input3, 1)
    val inputPy3 = MergeSort.sort(input3, 2)
    val closestPair3 = findClosetPair(inputPx3, inputPy3, 0, inputPx3.length - 1)
    println(closestPair3)
  }


  def distance = (p: Point, q: Point) => sqrt(pow(q._1 - p._1, 2) + pow(q._2 - p._2, 2))

  def closestLT3Pair(Px: Array[Point], idxStart: Int, idxEnd: Int): (Point, Point, Double) = {
    if (idxEnd - idxStart + 1 < 2) throw new IllegalArgumentException(
      f"""In order to measure distance, the number of points must be more than 2.""")
    val dis01 = distance(Px(idxStart), Px(idxStart + 1))
    if (idxEnd - idxStart + 1 == 2) return (Px(idxStart), Px(idxStart + 1), dis01)

    val dis02 = distance(Px(idxStart), Px(idxStart + 2))
    val dis12 = distance(Px(idxStart + 1), Px(idxStart + 2))
    val minPair = if (dis01 < dis02) {
      if (dis01 < dis12) (Px(idxStart), Px(idxStart + 1), dis01) else (Px(idxStart + 1), Px(idxStart + 2), dis12)
    } else {
      if (dis02 < dis12) (Px(idxStart), Px(idxStart + 2), dis02) else (Px(idxStart + 1), Px(idxStart + 2), dis12)
    }
    return minPair
  }

  /**
    * Find closest pair given an array of 2D points
    *
    * @param Px       Copied points sorted by x-coordinate
    * @param Py       Copied points sorted by y-coordinate
    * @param idxStart Start index of sub-array in recursive call
    * @param idxEnd   End index of sub-array in recursive call
    * @return Pairs with minimum distance
    */
  def findClosetPair(Px: Array[Point], Py: Array[Point], idxStart: Int, idxEnd: Int): (Point, Point, Double) = {
    if (idxEnd - idxStart + 1 <= 3) {
      return closestLT3Pair(Px, idxStart, idxEnd)
    }
    val mid = idxStart + (idxEnd - idxStart) / 2
    val pairLeft = findClosetPair(Px, Py, idxStart, mid)
    val pairRight = findClosetPair(Px, Py, mid + 1, idxEnd)
    val minPair = if (pairLeft._3 < pairRight._3) pairLeft else pairRight
    val splitPair = findSplitClosestPair(Px, Py, Px(mid)._1, minPair._3)
    if (splitPair != null) splitPair else minPair
  }

  def findSplitClosestPair(Px: Array[Point], Py: Array[Point], avgX: Int, minDis: Double) = {
    var best: (Point, Point, Double) = null
    val minStrip = avgX - minDis
    val maxStrip = avgX + minDis
    val sortedStrip = Py.filter(y => y._1 >= minStrip && y._1 <= maxStrip.toInt)
    for (i <- 0 to sortedStrip.size - 1) {
      val minL = min(7, sortedStrip.size - i)
      for (j <- i + 1 to minL) {
        val d = distance(Py(i), Py(j))
        if (d < minDis) {
          best = (Py(i), Py(j), d)
        }
      }
    }
    best
  }

  def generatePoints(number: Int = 100, coordLimit: Int = 100) = {
    val rd = Random.self
    rd.setSeed(10)
    //Use array of tuple2 gains good performance since Tuple2 is specialized for primitive type Int.
    val input = new Array[Point](number)
    (0 to number - 1).foreach(input(_) = (rd.nextInt(coordLimit), rd.nextInt(coordLimit))
    )
    input
  }
}