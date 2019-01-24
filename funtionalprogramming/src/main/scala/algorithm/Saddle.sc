def sd(in:List[List[Int]]):Option[(Int,Int)]={
  in.zipWithIndex foreach { (i,idx)=>
    val min = i.zipWithIndex.minBy(_._1)
    if(in.forall(c=>min._1 >= c(min._2))) return Some((idx,min._2))

  }
  return None
}

sd(List(List(1,2,3),
        List(4,5,6),
        List(7,8,9)))

sd(List(List(1, 2, 3),
        List(4, 5, 6),
        List(10,18,4)))

