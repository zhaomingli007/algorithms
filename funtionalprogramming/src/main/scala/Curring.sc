//Functions return functions
def sum(f: Int => Int): (Int, Int) => Int = {
  def sumF(a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sumF(a + 1, b)
  }
  sumF
}

def fact(x: Int): Int = {
  def loop(acc: Int, n: Int): Int = {
    if (n == 0) acc else loop(acc * n, n - 1)
  }
  loop(1, x)
}

def sumInts = sum(x => x)
def sumCubes = sum(x => x * x * x)
def sumFacts = sum(fact)

//passing two parameters to returned function (sumF)
sumCubes(1, 10) + sumFacts(10, 20)

//Avoid middleman
sum(x => x * x * x)(1, 10)

//Multiple parameter lists

def sumM(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 0 else f(a) + sumM(f)(a + 1, b)
}
sumM(x => x * x * x)(1, 10)

//Production
def product(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 1
  else f(a) * product(f)(a + 1, b)
product(x => x * x)(3, 4)

def factorial2(n: Int) = product(x => x)(1, n)
factorial2(5)


//Using a common method map reduce to combine sum and product
def mapReduce(f: Int => Int, combine: (Int, Int) => Int, initial: Int)(a: Int, b: Int): Int = {
  if (a > b) initial else combine(f(a), mapReduce(f, combine, initial)(a + 1, b))
}

def product1(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (a, b) => a * b, 1)(a, b)
product1(x => x * x)(3, 4)

def sum1(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (a, b) => a + b, 0)(a, b)
sum1(x => x * x * x)(1, 10)