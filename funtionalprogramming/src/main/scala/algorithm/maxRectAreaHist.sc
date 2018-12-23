//Greedy with recursive call
def mrah(hist:Array[Int]):Int={
  case class Bar(height:Int,index:Int)
  def f(s : List[Bar],r:List[Bar]):Int = {
    def area(bar:Bar):Int = bar.height*(hist.length - r.length - bar.index)
    (s, r) match {
      case (        Nil,        Nil)                                            => 0 
      case (          s, bar::rrest) if s.isEmpty || bar.height >= s.head.height => f(bar::s, rrest)
      case ((bar::rest),          _)                                            => f(      rest, r) max area(bar)
    }
  }
  f(Nil,hist.toList.zipWithIndex.map(Bar(_,_)))
}


//Greedy with recursive call 2
import scala.annotation.tailrec


def mrah2(hist:Array[Int]):Int={
  case class Bar(height:Int,index:Int)
  @tailrec def f(s : List[Bar],r:List[Bar],maxArea:Int ):Int = {
    def area(bar:Bar):Int = bar.height*(hist.length - r.length - bar.index )
    (s, r) match {
      case (        Nil,        Nil)                                             =>  maxArea
      case (          s, bar::rrest) if s.isEmpty || bar.height >= s.head.height => f(bar::s, rrest,maxArea)
      case ((bar::Nil),          _)                                              => f(   Nil, r,maxArea max bar.height*(hist.length - r.length)) 
      case ((bar::rest),          _)                                             => f(  rest, r,maxArea max area(bar)) 
    }
  }
  f(Nil,hist.toList.zipWithIndex.map(Bar(_,_)),0)
}


/**
   * Stack based solution to maximum rectangle in histogram problem
   * stack always has (h, x) such that h is in increasing order and x is the earliest index at which h can be spanned
   * O(n) - stack can be atmost size of remaining; no recursive step repeats previous; size of remaining never increases
   *
   * @param heights heights of histogram
   * @return area of largest rectangle under histogram
   */
def maxRectangleInHistogram(heights: Array[Int]): Int = {
    @tailrec def solve(stack: List[(Int, Int)], remaining: List[(Int, Int)],maxArea:Int): Int = {
      def area(x: Int, y: Int) = (heights.length - remaining.length - x) * y
      (stack, remaining) match {
        case (           Nil,          Nil)           => maxArea
        case ((y, x) :: rest,          Nil)           => solve(          rest,          Nil, maxArea max area(x, y)) 
        case ((y, x) :: rest, (h, i) :: hs) if h <= y => solve(          rest, (h, x) :: hs, maxArea max area(x, y)) 
        case (             _,  block :: hs)           => solve(block :: stack,           hs, maxArea)
      }
    }
    solve(Nil, heights.toList.zipWithIndex,0)
  }


mrah(Array(2,1,2))
mrah2(Array(2,1,2))
maxRectangleInHistogram(Array(2,1,2))
