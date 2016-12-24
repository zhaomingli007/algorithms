

/**
  * Created by zhao on 12/24/16.
  * <br>
  * Functions that take other functions as parameters or that
  * return functions as results are call higher order functions
  *
  */

/**
  * Take the sum of the integers between a and b
  */
def sumInts(a: Int, b: Int): Int =
  if (a > b) 0 else a + sumInts(a + 1, b)

/**
  * Take the sum of cubes of all the integers between a and b
  */


def sumCubes(a: Int, b: Int): Int =
  if (a > b) 0 else cube(a) + sumCubes(a + 1, b)

/**
  * Define a sum function for common use
  * f is a function type
  */

def sum(f: Int => Int, a: Int, b: Int): Int = {
  if (a > b) 0 else f(a) + sum(f, a + 1, b)
}

def sumInts1(a: Int, b: Int) = sum(x => x, a, b)
//Anonymous function
def sumCubes1(a: Int, b: Int) = sum(x => x * x * x, a, b)
def sumFactorials(a: Int, b: Int) = sum(fact, a, b)
sumInts1(1, 3)
sumCubes1(1, 4)
sumFactorials(1, 5)

def id(x: Int) = x
def cube(x: Int): Int = x * x * x
def fact(x: Int): Int = {
  def loop(acc: Int, n: Int): Int = {
    if (n == 0) acc else loop(acc * n, n - 1)
  }
  loop(1, x)
}

/**
  * Tail recursive of factorial
  *
  * @param f
  * @param a
  * @param b
  * @return
  */
def sum2(f: Int => Int, a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc + f(a))
  }
  loop(a, 0)
}
sum2(x => x * x, 3, 5)