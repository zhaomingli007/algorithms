val pair: List[(Char,Int)] = ('a',1)::('b',2)::('c',3)::Nil
pair.map(x=> x match {case(ch,num) => ch})
//Even shorter
pair.map{case(ch,num)=>ch}
