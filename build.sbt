name := "scala-app"
version := "19.08.00"

scalaVersion := "2.13.0"
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"