package algorithm.stfd

/**
  * Created by zhao on 1/6/18.<br/>
  * 1. Use base 2 representation to avoid overflow when do integer multiply.
  * 2. Function signature: define parameters and return value as intuitive as it is.
  * 3. Use devide and conque method with karatsuba algorithm
  */
object KaratsubaMultiply {

  def main(args: Array[String]) {
    val input = Array(38, 27, 43, 3, 9, 82, 10)

    val number1 = BigInt("3141592653589793238462643383279502884197169399375105820974944592")
    val number2 = BigInt("2718281828459045235360287471352662497757247093699959574966967627")
    //    val a = BigInt(number1)
    //    val number1 = BigInt(1234)
    //    val number2 = BigInt(5678)

    val res = multiply(number1, number2)
    println(res)
  }

  def multiply(first: BigInt, second: BigInt): BigInt = {
    if (first < 10 && second < 10) {
      //Only multiply single size digits
      return first * second
    } else {
      val n = Math.max(first.bitLength, second.bitLength)
      //Find the middle index of the max value of the two numbers, plus the surplus (module) for odd number.
      val mid = n / 2 + n % 2

      //Split the two numbers given middle index.
      val a = first >> mid //Higher half
      val a_b = (a, first - (a << mid)) //Lower half
      val c = second >> mid
      val c_d = (c, second - (c << mid))

      //Calculate a*c,b*d
      val ac = multiply(a_b._1, c_d._1)
      val bd = multiply(a_b._2, c_d._2)
      //Calculate (a+b)*(c+d)
      val aplusb = a_b._1 + a_b._2
      val cplusd = c_d._1 + c_d._2
      val x = multiply(aplusb, cplusd)

      //Apply karatsuba algorithm
      val r = (ac << (2 * mid)) + bd + ((x - ac - bd) << mid)
      println(s"r: $r")
      r
    }
  }


}
