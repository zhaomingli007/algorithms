package algorithm.leetcode.rnd1

import scala.collection.mutable.ArrayBuffer

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 */
object RemoveNthNodeFromEndList {

  case class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x

  }

  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    val indices = ArrayBuffer[ListNode]()
    var cur = head
    while (cur != null) {
      indices.append(cur)
      cur = cur.next
    }

    if(head == null) head
    else if (indices.length <= n) head.next
    else {
      val pre = indices(indices.length - n - 1)
      val next = if (n == 1) null else indices(indices.length - n + 1)
      pre.next = next
      head
    }
  }

  def removeNthByElement(head: ListNode, n: Int): ListNode = {
    var pre = head
    var post = head.next
    if (pre.x == n) pre.next
    else {
      while (post != null && post._x != n) {
        pre = pre.next
        post = post.next
      }
      if (pre != null && post != null) pre.next = post.next
      head
    }

  }


}
