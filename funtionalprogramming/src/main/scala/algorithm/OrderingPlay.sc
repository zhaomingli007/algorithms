import scala.util._


//Definition for singly-linked list.
case class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

val l1 = ListNode(1)
l1.next = ListNode(4)
l1.next.next = ListNode(2)
l1.next.next.next = ListNode(8)
l1.next.next.next.next = ListNode(5)
l1.next.next.next.next.next = ListNode(3)

val pairs = Array(ListNode(1),ListNode(4),ListNode(2), ListNode(8), ListNode(5),ListNode(3))
Sorting.quickSort(pairs)(Ordering.by(_.x))
pairs
//val pairs1 = Array(("a", 5, 2), ("c", 3, 1), ("e", 2, 1) ,("b", 1, 3))
//
//Sorting.quickSort(pairs1)(Ordering[(String,Int)].on(x => (x._1,x._3 )))
//pairs1