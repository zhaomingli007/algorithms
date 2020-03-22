package algorithm.leetcode.rnd1

import org.scalatest.FunSuite

class MergeSortedListTest extends FunSuite {

  test("testMergeTwoLists") {

    val l1 = MergeSortedList.ListNode(1)
    l1.next = MergeSortedList.ListNode(2)
    l1.next.next = MergeSortedList.ListNode(4)


    val l2 = MergeSortedList.ListNode(1)
    l2.next = MergeSortedList.ListNode(3)
    l2.next.next = MergeSortedList.ListNode(4)


    val m = MergeSortedList.mergeTwoLists(l1, l2)

    var prtm = m
    while(prtm.next != null) {print(s"${prtm.x} -> "); prtm =prtm.next }
    println(prtm.x)

  }

}
