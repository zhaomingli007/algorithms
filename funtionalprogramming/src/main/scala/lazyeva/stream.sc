
def isPrime(n: Int): Boolean = {
  	if (n < 1) false
  	else if (n == 1) true
  	else (2 until n) forall (d => n % d != 0)
  }                                               //> isPrime: (n: Int)Boolean
((1000 to 10000) filter isPrime)(1)

//it constructs all prime numbers between 1000 and 10000 in a list, 
//but only ever looks at the first two elements of that list.
((1000 to 10000).toStream filter isPrime)(2)

1 #:: List(2,3,4).toStream
