import org.apache.spark.sql.SparkSession
import scala.language.implicitConversions
import org.apache.spark.sql.functions._
lazy val spark =  SparkSession.getActiveSession match {
    case Some(e) => e
    case None => SparkSession.builder
                              .appName("sparkapp")
                              .master("local[2]")
                              .enableHiveSupport
                              .getOrCreate
  }

val dataset = Array[String]("a","a","b","b","b","c","c","d","e","e","e","e","e","e","e")

import spark.implicits._
val ds = spark.createDataset(dataset)
val all = ds.count
ds.groupBy("value").count.withColumn("ex",log2('count) / all).select(sum('ex) as  "r").show
