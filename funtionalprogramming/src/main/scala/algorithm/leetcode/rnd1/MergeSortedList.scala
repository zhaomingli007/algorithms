package algorithm.leetcode.rnd1

object MergeSortedList {

  //Definition for singly-linked list.
  case class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    if (l1 == null) return l2
    if (l2 == null) return l1
    if (l1.x < l2.x) {
      l1.next = mergeTwoLists(l1.next, l2)
      l1
    } else {
      l2.next = mergeTwoLists(l1, l2.next)
      l2
    }
  }

}
