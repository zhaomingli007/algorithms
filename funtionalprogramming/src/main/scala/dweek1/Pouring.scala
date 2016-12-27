package dweek1

/**
  * Created by zhaomingli on 16/12/27.
  */
class Pouring(capacity: Vector[Int]) {
  //States
  type State = Vector[Int] //state is an alias

  val initialState = capacity map (x => 0)

  //Moves

  trait Move

  case class Empty(glass: Int) extends Move

  case class Fill(glass: Int) extends Move

  case class Pour(from: Int, to: Int) extends Move

  val glasses = 0 until capacity.length

  val moves =
    (for (g <- glasses) yield Empty(g)) ++
      (for (g <- glasses) yield Fill(g)) ++
      (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))

}
