package mist.gen

import java.util.Properties

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import scala.io.Source

/**
  * Created by zhao on 1/7/17.
  */
object KafkaProducer {

  def main(args: Array[String]) {
    val props = new Properties
    props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094")
    props.put("acks", "all")
    //    props.put("retries", 0l)
    //    props.put("batch.size", 16384l)
    //    props.put("linger.ms", 1l)
    //    props.put("buffer.memory", 33554432l)
    props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[Integer, String](props)

    val sourceFile = "/tmp/hello_head.txt"
    val lines = Source.fromFile(sourceFile).getLines()
    var i = 0
    var errCount = 0
    lines foreach {
      line =>
        i += 1
        val myRecord = new ProducerRecord[Integer, String]("userevent1", i, line)
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
        Thread.sleep(15)
    }
    println("err count: " + errCount)
    producer.close()
  }

}
