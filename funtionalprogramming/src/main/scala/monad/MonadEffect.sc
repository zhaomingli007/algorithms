//Monads and Effect (side effect like exception)
import scala.util._
def answer(num:Int): Try[Int] = {
  if(num == 30) Success(30)
  else Failure(new Exception("error"))
}
answer(12) match {
  case Success(t) => t
  case fail @ Failure(exc) => fail
}
//Monads and Latency - non-blocking asynchronous program
import scala.concurrent._
import ExecutionContext.Implicits.global
// trait Future[T]{
//   def onComplete[U](callback: Try[T] => U)
//   (implicit executor:ExecutionContext):Unit
// }
// object Future {
//   def apply[T](body: ⇒ T)(implicit executor: ExecutionContext): Future[T]
// }

// The function to be run asyncronously
lazy val answerToLife: Future[Int] = future {
  42
}

// These are various callback functions that can be defined  
answerToLife onComplete {
  case Success(result) => result
  case Failure(t) => println("An error has occured: " + t.getMessage)
}
answerToLife onSuccess {
  case result => result
}

answerToLife onFailure {
  case t => println("An error has occured: " + t.getMessage)
}
// answerToLife.now
