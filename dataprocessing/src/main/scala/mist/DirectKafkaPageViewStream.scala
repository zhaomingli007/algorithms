package mist

import mist.gen.PageView
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
  * Created by zhao on 1/9/17.
  */
object DirectKafkaPageViewStream {

  def main(args: Array[String]) {

    // Create the context
    val ssc = new StreamingContext("local[2]", "PageViewStream", Seconds(2),
      System.getenv("SPARK_HOME"), StreamingContext.jarOfClass(this.getClass).toSeq)
    val sc = ssc.sparkContext


    // Create a ReceiverInputDStream on target host:port and convert each line to a PageView

    ssc.checkpoint("file:///Users/zhao/tmp/checkpoint_pageview")

    val topicsSet = Set("pageview")


    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "stream_group_1",
      //      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean))


    val pageViews = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topicsSet, kafkaParams)
    )
      .map(x => PageView.fromString(x.value()))





    val pageCounts = pageViews.map(view => view.url).countByValue()

    // Return a sliding window of page views per URL in the last ten seconds
    val slidingPageCounts = pageViews.map(view => view.url)
      .countByValueAndWindow(Seconds(10), Seconds(2))


    // Return the rate of error pages (a non 200 status) in each zip code over the last 30 seconds
    val statusesPerZipCode = pageViews.window(Seconds(30), Seconds(2))
      .map(view => ((view.zipCode, view.status)))
      .groupByKey()
    val errorRatePerZipCode = statusesPerZipCode.map {
      case (zip, statuses) =>
        val normalCount = statuses.count(_ == 200)
        val errorCount = statuses.size - normalCount
        val errorRatio = errorCount.toFloat / statuses.size
        if (errorRatio > 0.05) {
          "%s: **%s**".format(zip, errorRatio)
        } else {
          "%s: %s".format(zip, errorRatio)
        }
    }

    // Return the number unique users in last 16 seconds
    val activeUserCount = pageViews.window(Seconds(16), Seconds(2))
      .map(view => (view.userID, 1))
      .groupByKey()
      .count()
      .map("Unique active users: " + _)

    // An external dataset we want to join to this stream
    val userList = ssc.sparkContext.parallelize(Seq(
      1 -> "Patrick Wendell",
      2 -> "Reynold Xin",
      3 -> "Matei Zaharia"))

    //    pageCounts.print()

    val urlCategory = "none"
    pageCounts.map {
      kv =>
        val put = new Put(Bytes.toBytes(urlCategory + System.currentTimeMillis()))
        put.addColumn(Bytes.toBytes("pcount"), Bytes.toBytes(kv._1), Bytes.toBytes(kv._2))
        (kv._1, put)
    }.foreachRDD {
      rdd =>
        val conf = HBaseConfiguration.create()
        conf.addResource(new Path("/etc/hadoop/conf/core-site.xml"))
        conf.addResource(new Path("/etc/hbase/conf/hbase-site.xml"))
        conf.set(TableOutputFormat.OUTPUT_TABLE, "pageCounts")

        conf.set("mapreduce.job.output.key.class", classOf[Text].getName)
        conf.set("mapreduce.job.output.value.class", classOf[LongWritable].getName)
        conf.set("mapreduce.outputformat.class", classOf[TableOutputFormat[Text]].getName)
        rdd.saveAsNewAPIHadoopDataset(conf)

    }

    //    slidingPageCounts.print()
    //    errorRatePerZipCode.print()
    //    activeUserCount.print()

    // Look for users in our existing dataset and print it out if we have a match
    //    pageViews.map(view => (view.userID, 1))
    //      .foreachRDD((rdd, time) => rdd.join(userList)
    //        .map(_._2._2)
    //        .take(10)
    //        .foreach(u => println("Saw user %s at time %s".format(u, time))))

    ssc.start()
    ssc.awaitTermination()
  }

}
