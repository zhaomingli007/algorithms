package algorithm.leetcode.rnd1

import org.scalatest.FunSuite

class NextPermutationTest extends FunSuite {

  test("testNextPermutation") {
    for(i<-4 to 0) println(i)
    val input = Array(2,3,6,5,4,1)
    NextPermutation.nextPermutation(input)
    assertResult(Array(2,4,1,3,5,6))(input)


  }

}
