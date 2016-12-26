def removeAt(n: Int, xs: List[Int]) = (xs take n) ::: (xs drop n + 1)

removeAt(2, List(1, 2, 3, 4))
def removeAt2(n: Int, xs: List[Int]) = (xs take n) ++ (xs drop n + 1)
removeAt2(3, List(1, 2, 3, 4, 5, 6))


def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => List()
  case y :: ys => reverse(ys) ++ List(y)
}

