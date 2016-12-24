
/**
  * Not tail recursive function
  *
  * @param x
  * @return
  */
def fact(x: Int): Int =
  if (x == 0) 1 else x * fact(x - 1)

fact(4)

/**
  * Tail recursive version
  *
  * @param x
  * @return
  */
def factTailRec(x: Int): Int = {
  def loop(acc: Int, n: Int): Int = {
    if (n == 0) acc else loop(acc * n, n - 1)
  }
  loop(1, x)
}

factTailRec(4)


