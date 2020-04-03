name := "spark-app"
version := "20.04.00"

scalaVersion := "2.11.12"
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.5"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.5"
