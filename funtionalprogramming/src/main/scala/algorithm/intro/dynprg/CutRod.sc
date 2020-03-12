/**
 * Recursive approach, time complexity 2^n
 */

val p = Array(1, 5, 8, 9, 10, 17, 17, 20, 24, 30)

def cutrod(p: Array[Int], n: Int): Int = {
  if (n == 0) return 0
  var q = Integer.MIN_VALUE
  for (i <- 0 until n) q = Math.max(q, p(i) + cutrod(p, n - i - 1))
  q
}

cutrod(p, 1)
cutrod(p, 2)
cutrod(p, 3)
cutrod(p, 4)
cutrod(p, 5)
cutrod(p, 6)
cutrod(p, 7)
cutrod(p, 8)
cutrod(p, 9)
cutrod(p, 10)


/**
 * Top down with memorization
 */
def cutrodMemo(p: Array[Int], n: Int): Int = {

  ???
}