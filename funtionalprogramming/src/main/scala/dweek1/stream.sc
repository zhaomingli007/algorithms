//Stream is similar to list, but tails are evaluated on demand.

val s = Stream(1, 2, 3)
val si = (1 to 1000).toStream
((1000 to 10000).toStream filter isPrime) (1)

def isPrime(n: Int) = (2 until n) forall (n % _ != 0)