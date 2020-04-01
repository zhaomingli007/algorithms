package algorithm.leetcode.rnd1

import org.scalatest.FunSuite
class MergeKSortedListsTest extends FunSuite {

  test("testMergeKLists") {
    val l1 = MergeKSortedLists.ListNode(1)
    l1.next = MergeKSortedLists.ListNode(2)
    l1.next.next = MergeKSortedLists.ListNode(4)
    val l2 = MergeKSortedLists.ListNode(1)
    l2.next = MergeKSortedLists.ListNode(3)
    l2.next.next = MergeKSortedLists.ListNode(4)

    val m = MergeKSortedLists.mergeKLists(Array(l1,l2))

    var prtm = m
    while (prtm.next != null) {
      print(s"${prtm.x} -> "); prtm = prtm.next
    }
    println(prtm.x)
  }

}
