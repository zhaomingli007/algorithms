package mist

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{ForeachWriter, Row, SaveMode, SparkSession}
import org.apache.spark.streaming.kafka010.ConsumerStrategies._
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by zhao on 1/2/17.
  */
object DirectKafkaMasterDataset {

  def main(args: Array[String]) {

    val Array(brokers, subscribeType, topics) = Array("localhost:9092",
      "userevent1")

    val sparkConf = new SparkConf().setMaster("local[3]").setAppName("DirectKafkaWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(10))
    ssc.checkpoint("file:///tmp/checkpoint")

    val topicsSet = topics.split(",").toSet


    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092,localhost:9093,localhost:9094",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "stream_group_1",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean))

    val messages = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topicsSet, kafkaParams)
    )
    val spark = SparkSession
      .builder
      .master("local[3]")
      .appName("StructuredKafkaWordCount")
      .getOrCreate()


    messages
      .filter(x => x.value.contains("processed:{"))
      .map {
        line =>
          val ymd = line.value.substring(0, 13).replace(" ","-")
          val ts = line.value.substring(0, 19)
          val tails = line.value.substring(line.value.indexOf(":{\"request\"") + 2)
          raw""" {"ymd":"$ymd","record_time":"$ts",$tails """
      }
      .foreachRDD {
        rdd =>
          if (!rdd.isEmpty()) {
            val jsonDF = spark.read.json(rdd)
            //Save to arch master repository
            jsonDF
              .write.mode(SaveMode.Append)
//              .option("compression", "bzip2")
              .partitionBy("ymd")
              .json("hdfs://localhost:9000/data/json/hist2/my.json")

            //Save to data model repository as parquet format
            jsonDF.createOrReplaceTempView("tmp")
            spark
              .sql("select ymd,record_time, request.imsi1,version,request.tag,request.aid,request.apil,request" +
                ".defender.bBrand," +
                "request.props.`ro.product.model` ,request.`X-Real-IP` from " +
                "tmp")
              .write.mode(SaveMode.Append)
              .partitionBy("ymd")
              .parquet("hdfs://localhost:9000/data/parquet/hist2/my.parquet")
          }
      }
    ssc.start
    ssc.awaitTermination

    //    String result = jobj.get("test").toString();

    //
    //    val spark = SparkSession
    //      .builder
    //      .master("local[3]")
    //      .appName("StructuredKafkaWordCount")
    //      .getOrCreate()
    //
    //
    //    import spark.implicits._
    //
    //    val lines = spark
    //      .readStream
    //      .format("kafka")
    //      .option("kafka.bootstrap.servers", brokers)
    //      .option("startingOffsets", "earliest")
    //      .option(subscribeType, topics)
    //      .load
    //      .selectExpr("CAST(value AS STRING)")
    //      .as[String]
    //
    //    lines.map {
    //      x =>
    //        val jobj = new Gson()
    //        val obj = jobj.fromJson(x, classOf[JsonObject])
    //        val imsi = obj.get("request.imsi1")
    //        obj.get("record_time").getAsString + "," + (if (imsi == null || imsi.isJsonNull) "" else imsi.getAsString)
    //    }
    //      .writeStream
    //      .format("console")
    //      .option("checkpointLocation", "file:///tmp/checkpoint/")
    //      .start
    //      .awaitTermination

  }


}
