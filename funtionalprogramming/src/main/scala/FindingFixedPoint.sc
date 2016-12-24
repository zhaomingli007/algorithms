import math.abs

def isCloseEnough(x: Double, y: Double) = abs((x - y) / x) / x < 0.0001

def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  //Iterate calling function to get the fixed point
  def iterate(guess: Double): Double = {
    println("guess:" + guess)
    val next = f(guess)
    if (isCloseEnough(guess, next)) next
    else iterate(next)
  }
  iterate(firstGuess)
}

fixedPoint(x => 1 + x / 2)(1)

abs((2 - 1.99998) / 2) / 2

//average the successive value to make it converge
def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

def sqrt(x: Double) = fixedPoint(averageDamp(y => x / y))(1.0)
sqrt(2)

