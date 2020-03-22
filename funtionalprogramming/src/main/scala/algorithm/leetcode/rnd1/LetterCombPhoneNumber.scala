package algorithm.leetcode.rnd1

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */


object LetterCombPhoneNumber {

  lazy val digitMatch = (digit: String) => digit match {
    case "2" => "abc"
    case "3" => "def"
    case "4" => "ghi"
    case "5" => "jkl"
    case "6" => "mno"
    case "7" => "pqrs"
    case "8" => "tuv"
    case "9" => "wxyz"
    case _ => ""
  }

  /**
   *
   * @param digitsAsStr string representation of digits, no space no separator between each char.
   */
  def getCombinations(digitsAsStr: String) = {

    val letters = digitsAsStr.split("").map(digitMatch).toList
    println(letters)

    val res = letters.foldLeft(List(""))((l,s)=> for(i<-l; j<-s) yield i + j)
    println(res)

    res

  }


}

