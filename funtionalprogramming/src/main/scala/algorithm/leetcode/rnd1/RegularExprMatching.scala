package algorithm.leetcode.rnd1


object RegularExprMatching {

  /**
   * Using DP method
   *
   * @param s
   * @param p
   * @return
   */
  def isMatch(s: String, p: String): Boolean = {
    if (s == null || p == null)
      return false
    val dp = Array.ofDim[Boolean](s.length + 1, p.length + 1)
    dp(0)(0) = true
    for (j <- 2 to p.length by 2)
      if (p.charAt(j - 1) == '*' && dp(0)(j - 2)) dp(0)(j) = true

    for (i <- 1 to s.length)
      for (j <- 1 to p.length) {
        val curS = s.charAt(i - 1)
        val curP = p.charAt(j - 1)
        if (curS == curP || curP == '.')
          dp(i)(j) = dp(i - 1)(j - 1)
        else if (curP == '*') {
          val preCurP = p.charAt(j - 2)
          if (preCurP != '.' && preCurP != curS)
            dp(i)(j) = dp(i)(j - 2)
          else
            dp(i)(j) = dp(i)(j - 2) || dp(i - 1)(j - 2) || dp(i - 1)(j)
        }
      }
    dp(s.length)(p.length)
  }

}
