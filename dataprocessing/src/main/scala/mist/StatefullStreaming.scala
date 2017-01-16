package mist

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, State, StateSpec, StreamingContext}

import scala.util.Random

/**
  * Created by zhao on 1/2/17.
  */
object StatefullStreaming {

  def main(args: Array[String]) {

    val Array(brokers, subscribeType) = args
    val topics = "userevent1"

    val sparkConf = new SparkConf().setMaster("local[3]").setAppName("DirectKafkaWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(10))

    //    ssc.checkpoint("hdfs://localhost:9000//app/spark/checkpoint")
    ssc.checkpoint("hdfs://localhost:9000/app/checkpoint")


    val topicsSet = topics.split(",").toSet

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092,localhost:9093",
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

    //    val wordMapRdd = messages.map(_.value).flatMap(_.split(" ")).map((_, 1L))
    //    val wordCount = wordMapRdd.reduceByKey(_ + _)
    //    wordCount.print
    //    wordCount.foreachRDD {
    //      r =>
    //        r.foreach(x => println("key: " + x._1 + ",value: " + x._2))
    //    }

    //--- stateful --
    def updateUserEvents(key: Int, value: Option[UserEvent], state: State[UserSession]): Option[UserSession] = {
      def updateUserSessions(newEvent: UserEvent): Option[UserSession] = {
        val existingEvents: Seq[UserEvent] =
          state
            .getOption()
            .map(_.userEvents)
            .getOrElse(Seq[UserEvent]())

        val updatedUserSessions = UserSession(newEvent +: existingEvents)
        updatedUserSessions.userEvents.find(_.isLast) match {
          case Some(_) =>
            state.remove()
            Some(updatedUserSessions)
          case None =>
            state.update(updatedUserSessions)
            None

        }
      }

      value match {
        case Some(newEvent) =>
          updateUserSessions(newEvent)
        case _ if state.isTimingOut() => state.getOption()
      }
    }

    val stateSpec =
      StateSpec.function(updateUserEvents _).timeout(Seconds(6))

    //


    val spark = SparkSession
      .builder
      .appName("StructuredKafkaWordCount")
      .getOrCreate()


    import spark.implicits._
    val linesDF = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", brokers)
      .option("startingOffsets", "earliest")
      .option(subscribeType, topics)
      .load()
    val lines = linesDF
      .selectExpr("CAST(value AS STRING)")
      .as[String]


    val wordCounts = lines.flatMap(_.split(" ")).groupBy("value").count


    //    val query = wordCounts.writeStream
    //      .outputMode("complete")
    //      .format("console")
    //      .foreach(
    //        new ForeachWriter[Row] {
    //
    //          def open(partitionId: Long, version: Long): Boolean = {
    //            // open connection
    //            val config = HBaseConfiguration.create
    //            true
    //          }
    //
    //          def process(record: Row) = {
    //            // write string to connection
    //          }
    //
    //          def close(errorOrNull: Throwable): Unit = {
    //            // close the connection
    //          }
    //        }
    //      )
    //      .start()

    //    query.awaitTermination()


    def deserializeUserEvent(message: ConsumerRecord[String, String]) = {
      (1, UserEvent(message.key, message.value, Random.nextBoolean()))
    }


    messages
      .map(deserializeUserEvent)
      .mapWithState(stateSpec)
      .foreachRDD {
        rdd =>
          if (!rdd.isEmpty()) {
            rdd.foreachPartition {
              //HBase connection
              partitionOfRecords =>
                partitionOfRecords.foreach(kv => kv.get.userEvents.foreach(x => println("--printed data ---" + x.data)))
              //Close HBase conn
            }
          }
      }

    ssc.start
    ssc.awaitTermination


  }


}
