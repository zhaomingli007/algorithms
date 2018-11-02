lazy val root = project
  .in(file("."))
  .settings(
    name := "funnctionalprogramming",
    description := "functional programming",
    version := "1.0",
    scalaVersion := dottyLatestNightlyBuild.get
  )
resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)
val sparkVersion = "2.3.2"
resolvers += Resolver.sonatypeRepo("snapshots")

// libraryDependencies += ("org.apache.spark" %% "spark-core" % sparkVersion).withDottyCompat(scalaVersion)
// libraryDependencies += ("org.apache.spark" %% "spark-sql" % sparkVersion).withDottyCompat(scalaVersion)
// libraryDependencies += ("org.apache.spark" %% "spark-streaming" % sparkVersion).withDottyCompat(scalaVersion)
// libraryDependencies += ("org.apache.spark" %% "spark-hive" % sparkVersion).withDottyCompat(scalaVersion)

scalacOptions ++= { if (isDotty.value) Seq("-language:Scala2") else Nil }
