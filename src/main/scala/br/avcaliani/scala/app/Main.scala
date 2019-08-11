package br.avcaliani.scala.app

import br.avcaliani.scala.app.util.FSUtil
import br.avcaliani.scala.app.validator.{NameValidator, Validator}
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

object Main extends App {

  val dataPath = s"data/yob1997.csv"
  val outOk = s"output/yob1997.success.csv"
  val outErr = s"output/yob1997.err.csv"

  val sc = new SparkConf().setMaster("local[*]").setAppName("spark-app")
  val ss = SparkSession.builder().config(sc).getOrCreate()
  val fs = new FSUtil(ss, FileSystem.get(ss.sparkContext.hadoopConfiguration))

  val df: DataFrame = fs.read(dataPath)
  val validator: Validator = new NameValidator(ss)

  val dfValidated = validator.validate(df)
  val dropColumns: Array[String] = dfValidated.columns.filter(_.endsWith("_valid"))
  val dfOk  = dfValidated.filter(col("is_valid") === true)
  val dfErr = dfValidated.filter(col("is_valid") === false)

  fs.write(outOk, dfOk.drop(dropColumns: _*))
  fs.write(outErr, dfErr.drop(dropColumns: _*))

}