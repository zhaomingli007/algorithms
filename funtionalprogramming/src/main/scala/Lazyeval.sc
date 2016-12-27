def expr = {
  val x = {
    print("x");
    1
  }
  lazy val y = {
    print("y");
    2
  }
  def z = {
    print("z");
    3
  }

  z + y + x + z + y + x
}
//xzyz
expr

def from(n: Int): Stream[Int] = n #:: from(n + 1)
val nats = from(0)
from(2)
val m4s = nats map (_ * 4)
(m4s take 100).toList

def sieve(s: Stream[Int]): Stream[Int] =
  s.head #:: sieve(s.tail filter (_ % s.head != 0))

val primes = sieve(from(2))
primes take (100) toList
