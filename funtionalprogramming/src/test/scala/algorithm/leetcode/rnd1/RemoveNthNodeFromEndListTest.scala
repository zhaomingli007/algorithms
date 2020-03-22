package algorithm.leetcode.rnd1

import org.scalatest.FunSuite
import algorithm.leetcode.rnd1.RemoveNthNodeFromEndList._

class RemoveNthNodeFromEndListTest extends FunSuite {

  test("remove nth node from end of the list") {
    val test = ListNode(0)
    (1 to 5).foldLeft(test) { (node, x) => node.next = ListNode(x); node.next }
    var prtNd = test
    while (prtNd != null) {
      print(prtNd.x+" ")
      prtNd = prtNd.next
    }
    println

    var res = RemoveNthNodeFromEndList.removeNthFromEnd(test, 1)
    while (res != null){
      print(res._x+" ")
      res = res.next
    }

  }

  test("remove ... 2"){
    val test = ListNode(0)
    (1 to 2).foldLeft(test) { (node, x) => node.next = ListNode(x); node.next }
    var prtNd = test
    while (prtNd != null) {
      print(prtNd.x+" ")
      prtNd = prtNd.next
    }
    println("result:")
    var res2 = RemoveNthNodeFromEndList.removeNthFromEnd(test, 4)
    while (res2 != null){
      print(res2._x+" ")
      res2 = res2.next
    }
  }

  test("remove ... 3"){
    val test = ListNode(1)
    var prtNd = test
    while (prtNd != null) {
      print(prtNd.x+" ")
      prtNd = prtNd.next
    }
    println("result:")
    var res2 = RemoveNthNodeFromEndList.removeNthFromEnd(test, 1)
    while (res2 != null){
      print(res2._x+" ")
      res2 = res2.next
    }
  }

  test("remove ... 4"){
    val test = ListNode(0)
    (1 to 2).foldLeft(test) { (node, x) => node.next = ListNode(x); node.next }
    var prtNd = test
    while (prtNd != null) {
      print(prtNd.x+" ")
      prtNd = prtNd.next
    }
    println("result:")
    var res2 = RemoveNthNodeFromEndList.removeNthFromEnd(test, 3)
    while (res2 != null){
      print(res2._x+" ")
      res2 = res2.next
    }
  }

}
