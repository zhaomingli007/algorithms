val t1 = System.nanoTime

val args = sc.getConf.get("spark.driver.args").split("\\s+")
val (confFile,oraHost,oraPort,oraSID,userName,password) = (args(0),args(1),args(2),args(3),args(4),args(5))

import scala.io.Source
import scala.util.parsing.json.JSON

def js2m(j:String):Option[Map[String,String]]= JSON.parseFull(j) match {
  case Some(ele) => Some(ele.asInstanceOf[Map[String, String]])
  case _ => None
}
def doSparkTransJob(param:Map[String,String])={
  println(s"Executing: ${param.mkString(",")}")

  val rcntQuery = s"""
    (select
        count(1) as CNT
    from ${param("dbSchema")}.${param("tableName")} t WHERE ${param("filter")}) tmp
  """   
  val rcnt = spark.read
      .format("jdbc")
      .option("url", s"""jdbc:oracle:thin:$userName/$password@//$oraHost:$oraPort/$oraSID""")
      .option("dbtable",s"$rcntQuery")
      .load()
      .collect()
      .map(row => row.getAs[Any]("CNT")).head

  val dbTableQuery = s"""
    (select
        ROW_NUMBER() OVER ( ORDER BY 1 ) ROW_NUMBER,
        t.*
    from ${param("dbSchema")}.${param("tableName")} t WHERE ${param("filter")}) tmp
  """      
  println(s"dbtable sub query $dbTableQuery")
  val jdbcDF = spark.read
    .format("jdbc")
    .option("url", s"""jdbc:oracle:thin:$userName/$password@//$oraHost:$oraPort/$oraSID""")
    .option("dbtable", s"$dbTableQuery")
    .option("partitionColumn", "ROW_NUMBER")
    .option("lowerBound", 1)
    .option("upperBound", rcnt.toString.toDouble.toLong)
    .option("numPartitions", s"""${param("numPartitions")}""").load


  val mask = param("maskColumns").split(",")
  val expr = jdbcDF.columns.map(col => if (mask.contains(col) ) s"""md5(${col}) as ${col}""" else col)

  val distDF = jdbcDF.selectExpr(expr: _*)
  val isEmptyPart = param("phyPartition").trim == ""
  val writer = if(isEmptyPart) distDF.write else distDF.write.partitionBy(param("phyPartition"))
  writer.parquet(s"""${param("hdfsPath")}/${param("tableName")}""")
  val duration = (System.nanoTime - t1) / 1e9d
  println(s"duration: $duration(s)")
}

val maps = Source.fromFile(s"$confFile").getLines.map(js2m).toList
maps.par.foreach{
  case Some(m) => doSparkTransJob(m)
  case None => println("Error parsing json entry")
}
