import scala.collection.mutable.Map

def twoSum(nums:Array[Int], target:Int):Array[Int] =  {
  val cache = Map.empty[Int, Int]
    nums.indices.foreach {
      i => 
      val e = nums(i)
      val idx = cache.get(target - e)
      idx match {
        case Some(i2) => return Array(i2, i)
        case None => None
      }
      cache.put(e, i)
  }
  Array()
}

twoSum(Array(3, 9, 1, 5, 7), 8)


