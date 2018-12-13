Seq(1,2,3,4,5).scan(1)(_ + _)
Seq(1,2,3,4,5).scanLeft(1)(_ + _)
Seq(1,2,3,4,5).scanRight(1)(_ + _)

Seq(1,2,3,4,5).foldLeft(1)(_ + _)
Seq(1,2,3,4,5).foldRight(1)(_ + _)
Seq(1,2,3,4,5).fold(1)(_ + _)

def maxSubArraySum(s: Array[Int]) = s.tail.scan(s.head)((s,x) => s + x max x ).max
maxSubArraySum(Array(-3,-2,-8,5,-1,6))