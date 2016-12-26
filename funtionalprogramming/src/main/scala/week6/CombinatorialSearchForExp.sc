object pairs {

  val n = 7


  def isPrime(n: Int) = (2 until n) forall (n % _ != 0)


  val flatPair = (1 until 7) flatMap (i =>
    (1 until i) map ((i, _))
    )

  flatPair filter (x => isPrime(x._1 + x._2))
  flatPair filter { case (x, y) => isPrime(x + y) }

  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j)

  def scalarProduct(xs: List[Double], ys: List[Double]): Double =
    (for ((x, y) <- xs zip ys) yield x * y).sum

  scalarProduct(List(1,2,3),List(4,5,6))
}