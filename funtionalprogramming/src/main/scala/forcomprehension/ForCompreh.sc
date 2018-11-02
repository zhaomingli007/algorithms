val names = Array("chris", "ed", "maurice")
val capNames = for(e <-  names) yield e.capitalize

val fruits = "apple" :: "banana" :: "orange" :: Nil
val out = for {
  e <- fruits
  if e.length>5 //filter
  } yield e
