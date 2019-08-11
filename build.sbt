name := "spark-app"
version := "19.08.00"

scalaVersion := "2.11.12"
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.3"
