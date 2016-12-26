//List takes constant time accessing head
// whereas vector take some time to find head.
// Vector is good for random access any element.
//If an access pattern is bulk operation like map,
// filter fold and reduce , vector will be preferable.
// List -> Linked list good for adding new element ,
// Vector -> Array good for accessing
val nums1 = List(1, 2, 3, -88)
val nums3 = 2 :: nums1
val nums5 = nums3 ::: List(4)

val nums = Vector(1, 2, 3, -88)
val nums2 = "a" +: nums
val nums4 = nums2 :+ "b"
val num6 = nums4 ++ Vector("c")


val r: Range = 1 until 5
val s: Range = 1 to 5
val t: Range = 1 to 10 by 2

val str = "Hello World"

str exists (_.isUpper)
str forall (_.isUpper)

val pairs = List(1, 2, 3) zip str
pairs.unzip

str flatMap (List(".", _))

nums.sum
nums.product
nums.max
nums.min


val nums6 = Vector(3, 4, 5, 2)

(nums zip nums6).map { x => x._1 * x._2 }.sum
(nums zip nums6).map { case (x, y) => x * y }.sum


def isPrime(n:Int) = (2 until n) forall (n % _ !=0)

isPrime(3)
isPrime(9)