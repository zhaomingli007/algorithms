package mist

import org.apache.spark.sql.SparkSession

/**
  * Created by zhao on 1/8/17.
  */
object Query {
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .master("local[3]")
      .appName("StructuredKafkaWordCount")
      .getOrCreate()
    println("count:"+spark.read.parquet("hdfs://localhost:9000/data/parquet/hist2/my.parquet").count)
    println("count json:"+spark.read.json("hdfs://localhost:9000/data/json/hist2/my.json").count)

  }
}
