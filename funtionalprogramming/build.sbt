lazy val root = project
  .in(file("."))
  .settings(
    name := "funnctionalprogramming",
    description := "functional programming",
    version := "1.0",
    //scalaVersion := "2.11.12"
    scalaVersion := dottyLatestNightlyBuild.get
  )
resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)
val sparkVersion = "2.4.0"
resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += ("org.apache.spark" %% "spark-core" % sparkVersion).withDottyCompat(scalaVersion.value)
libraryDependencies += ("org.apache.spark" %% "spark-sql" % sparkVersion).withDottyCompat(scalaVersion.value)
libraryDependencies += ("org.apache.spark" %% "spark-streaming" % sparkVersion).withDottyCompat(scalaVersion.value)
libraryDependencies += ("org.apache.spark" %% "spark-hive" % sparkVersion).withDottyCompat(scalaVersion.value)

scalacOptions ++= { if (isDotty.value) Seq("-language:Scala2") else Nil }
