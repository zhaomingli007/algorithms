def squareList2(xs: List[Int]): List[Int] =
  xs match {
    case Nil => Nil
    case y :: ys => y * y :: squareList(ys)
  }

def squareList(xs: List[Int]): List[Int] =
  xs map (x => x * x)

val nums = List(2, -4, 5, 7, 1)

nums filter (x => x > 0)
nums partition (x => x > 0)

nums takeWhile (x => x > 0)
nums dropWhile (x => x > 0)
nums span (x => x > 0)


val data = List("a", "a", "a", "b", "c", "c", "a")

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

pack(data)


def encode[T](xs: List[T]): List[(T, Int)] =
  pack(xs) map (y => (y.head, y.length))

encode(data)


