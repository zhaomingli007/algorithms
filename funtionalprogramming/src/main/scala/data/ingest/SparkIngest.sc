
val args = sc.getConf.get("spark.driver.args").split("\\s+")
val (confFile,userName,password) = (args(0),args(1),args(2))

import scala.io.Source
import scala.util.parsing.json.JSON
import scala.util.{Try,Success,Failure}
import scala.util.control.Exception
def js2m(j:String):Option[Map[String,String]]= JSON.parseFull(j) match {
  case Some(ele) => Some(ele.asInstanceOf[Map[String, String]])
  case _ => None
}
val maps = Source.fromFile(s"$confFile").getLines.map(js2m).toList
maps.par.foreach{
  case Some(m) => doSparkTransJob(m)
  case None => println("Error parsing json entry")
}

def doSparkTransJob(param:Map[String,String])={
  println(s"Executing: ${param.mkString(",")}")
  val jdbcDF = spark.read
    .format("jdbc")
    .option("url", s"""jdbc:oracle:thin:$userName/$password@//localhost:1521/xe""")
    .option("dbtable", s"""${param("dbSchema")}.${param("tableName")}""")
    .option("partitionColumn", s"""${param("partitionColumn")}""")
    .option("lowerBound", s"""${param("lowerBound")}""")
    .option("upperBound", s"""${param("upperBound")}""")
    .option("numPartitions", s"""${param("numPartitions")}""")
    .load()
    jdbcDF.write.partitionBy("PARTITION_COL").parquet(s"""${param("hdfsPath")}/${param("tableName")}""")
}