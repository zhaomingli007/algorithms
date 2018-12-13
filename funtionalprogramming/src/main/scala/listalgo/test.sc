import scala.concurrent.ExecutionContext.Implicits.global
val eventualStr = scala.concurrent.Future("str")

eventualStr map { f ->
  f 
}


for {
 str <- eventualStr 
} yield str

