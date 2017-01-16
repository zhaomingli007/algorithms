package mist.gen

import java.util.Properties

import com.jayway.jsonpath.JsonPath
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import scala.util.Random

/** Represents a page view on a website with associated dimension data. */
class PageView(val url: String, val status: Int, val zipCode: Int, val userID: Int)
  extends Serializable {
  override def toString(): String = {
    raw""" {"url":"$url","status":$status,"zipCode":$zipCode,"userId":$userID} """
  }
}

object PageView extends Serializable {
  def fromString(in: String): PageView = {
    val ctx = JsonPath.parse(in)
    new PageView(ctx.read("$.url"), ctx.read("$.status"), ctx.read("$.zipCode"), ctx.read("$.userId"))
  }
}

/**
  * Created by zhao on 1/9/17.
  */
object PageViewDataGen {
  val pages = Map("http://foo.com/" -> .7,
    "http://foo.com/news" -> 0.2,
    "http://foo.com/contact" -> .1)
  val httpStatus = Map(200 -> .95,
    404 -> .05)
  val userZipCode = Map(94709 -> .5,
    94117 -> .5)
  val userID = Map((1 to 100).map(_ -> .01): _*)

  def pickFromDistribution[T](inputMap: Map[T, Double]): T = {
    val rand = new Random().nextDouble()
    var total = 0.0
    for ((item, prob) <- inputMap) {
      total = total + prob
      if (total > rand) {
        return item
      }
    }
    inputMap.take(1).head._1 // Shouldn't get here if probabilities add up to 1.0
  }

  def getNextClickEvent(): String = {

    val id = pickFromDistribution(userID)
    val page = pickFromDistribution(pages)
    val status = pickFromDistribution(httpStatus)
    val zipCode = pickFromDistribution(userZipCode)
    new PageView(page, status, zipCode, id).toString()
  }

  def main(args: Array[String]) {
    val viewsPerSecond = 5
    val sleepDelayMs = (1000.0 / viewsPerSecond).toInt

    val props = new Properties
    props.put("bootstrap.servers", "localhost:9092")
    props.put("acks", "all")
    //    props.put("retries", 0l)
    //    props.put("batch.size", 16384l)
    //    props.put("linger.ms", 1l)
    //    props.put("buffer.memory", 33554432l)
    props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[Integer, String](props)


    while (true) {
      var errCount = 0
      var i = 0
      while (true) {
        Thread.sleep(sleepDelayMs)
        i += 1
        val myRecord = new ProducerRecord[Integer, String]("pageview", i, getNextClickEvent())
        producer.send(myRecord,
          new Callback() {
            def onCompletion(metadata: RecordMetadata, e: Exception) {
              if (e != null) {
                errCount += 1
                e.printStackTrace()
              }
              if (metadata != null)
                println("< " + metadata.offset())
              else
                println("record too large")
            }
          })
      }
    }
  }
}
