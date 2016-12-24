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


