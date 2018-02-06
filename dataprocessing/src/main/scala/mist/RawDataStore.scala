package mist

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.KafkaUtils

/**
  * Created by zhao on 1/19/17.
  */
object RawDataStore {

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .master("local[3]")
      .appName("StructuredKafkaWordCount")
      .getOrCreate()
  }

}
