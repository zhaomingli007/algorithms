
val nums = List(2, -4, 5, 7, 1)
def sum(xs: List[Int]) = 0 :: xs reduceLeft (_ + _)
def prod(xs: List[Int]) = 1 :: xs reduceLeft (_ * _)

sum(nums)
prod(nums)

def sum2(xs: List[Int]) = (xs foldLeft 0) (_ + _)
def prod2(xs: List[Int]) = (xs foldLeft 1.0) (_ * _)
prod2(nums)
prod2(Nil)
