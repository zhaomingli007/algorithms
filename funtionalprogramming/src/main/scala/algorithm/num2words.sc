def speak(num: Int, printZero: Boolean = true): String = {
  if (num < 0) s"negative ${speak(-num)}"
  else if (num >= 1000000000) s"${speak(num / 1000000000)} billion ${speak(num % 1000000000, false)}"
  else if (num >= 1000000) s"${speak(num / 1000000)} million ${speak(num % 1000000, false)}"
  else if (num >= 1000) s"${speak(num / 1000)} thousand ${speak(num % 1000, false)}"
  else if (num >= 100) s"${speak(num / 100)} hundred ${speak(num % 100, false)}"
  else if (num >= 20) (num/10) match {
            case 2 => s"twenty ${speak(num % 10, false)}"
            case 3 => s"thirty ${speak(num % 10, false)}"
            case 5 => s"fifty ${speak(num % 10, false)}"
            case n@_ => s"${speak(n).stripSuffix("t")}ty ${speak(num % 10, false)}"
        }
 else num match {
        case 0 => if (printZero) "zero" else ""
        case 1 => "one"
        case 2 => "two"
        case 3 => "three"
        case 4 => "four"
        case 5 => "five"
        case 6 => "six"
        case 7 => "seven"
        case 8 => "eight"
        case 9 => "nine"
        case 10 => "ten"
        case 11 => "eleven"
        case 12 => "twelve"
        case 13 => "thirteen"
        case 15 =>"fifteen";
        case n@_ => s"${speak(n-10).stripSuffix("t")}teen"
    }
}

speak(323237232)
