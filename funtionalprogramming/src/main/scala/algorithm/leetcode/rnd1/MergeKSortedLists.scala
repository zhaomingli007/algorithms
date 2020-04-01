package algorithm.leetcode.rnd1

import scala.collection.mutable.PriorityQueue

object MergeKSortedLists {


  //Definition for singly-linked list.
  case class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  def mergeKLists(lists: Array[ListNode]): ListNode = {
    val queue = PriorityQueue.empty[ListNode](
      Ordering.by((_:ListNode).x).reverse
    )
    lists.foreach(x => if (x != null) queue.enqueue(x))
    val dummy = ListNode()
    var tail = dummy
    while (!queue.isEmpty) {
      tail.next = queue.dequeue()
      tail = tail.next
      if (tail.next != null) queue.enqueue(tail.next)
    }
    dummy.next
  }

}
