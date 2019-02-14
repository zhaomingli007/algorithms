name := "functionalprogramming"

version := "0.1"

organization := "Aaron"

val sparkVersion = "2.4.0"
scalaVersion := "2.12.8"


libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-streaming" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.13"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
