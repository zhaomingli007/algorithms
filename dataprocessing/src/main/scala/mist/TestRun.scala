package mist

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by zhao on 1/7/17.
  */
object TestRun {

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .master("local[3]")
      .appName("StructuredKafkaWordCount")
      .getOrCreate()
    val csv = spark.read.textFile("hdfs://localhost:9000/data/raw/hello.2017-01-05.log")

    import spark.implicits._
    val json = csv
      .filter(_.contains("processed:{"))
      .map(line => "{\"record_time\":\"" + line.substring(0, 19) + "\"," + line.substring(line.indexOf(":{\"request\"") +
        2))
      .write
      //      .option("compression", "bzip2")
      .text("hdfs://localhost:9000/data/json/hist")

    val ojson = spark
      .read
      //      .option("compression", "bzip2")
      .json("hdfs://localhost:9000/data/json/hist")
    ojson.createOrReplaceTempView("tmp")
    spark
      .sql("select record_time, request.imsi1,version,request.tag,request.aid,request.apil,request" +
        ".defender.bBrand," +
        "request.props.`ro.product.model`,request.props.`gsm.sim.operator.alpha` ,request.`X-Real-IP` from tmp")
      .write.parquet("hdfs://localhost:9000/parquet/hist")

    val parquetInput = spark.read.parquet("hdfs://localhost:9000/parquet/hist")
    parquetInput.createOrReplaceTempView("par")
    spark
      .sql("select imsi1,substring(record_time,0,13) as dt,count(1) as cnt from par where imsi1 is not null and imsi1" +
        " " +
        "!=0 group by imsi1,substring(record_time,0,13) having cnt >1")
      .show(10)
    spark.sql("select * from par where imsi1='460023262426869'").show


  }
}
