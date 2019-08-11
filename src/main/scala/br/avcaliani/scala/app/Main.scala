package br.avcaliani.scala.app

import br.avcaliani.scala.app.util.{FSUtil, Timer}
import br.avcaliani.scala.app.validator.NameValidator
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.LoggerFactory

object Main extends App with Props {

  val log = LoggerFactory.getLogger(getClass)
  val APP_NAME     = getProperty(s"app.name")
  val DATA_PATH    = getProperty(s"hdfs.data")
  val OUT_PATH_OK  = getProperty(s"hdfs.output.success")
  val OUT_PATH_ERR = getProperty(s"hdfs.output.error")

  val sc = new SparkConf().setMaster("local[*]").setAppName(APP_NAME)
  val ss = SparkSession.builder().config(sc).getOrCreate()
  val fs = new FSUtil(ss, FileSystem.get(ss.sparkContext.hadoopConfiguration))


  Timer.start()
  val df: DataFrame = fs.read(DATA_PATH)
  log.info(s"Task::Read -> ${ Timer.stop() }s")


  Timer.start()
  val dfValidated = new NameValidator(ss).validate(df)
  val dropColumns: Array[String] = dfValidated.columns.filter(_.endsWith("_valid"))
  val dfOk  = dfValidated.filter(col("is_valid") === true)
  val dfErr = dfValidated.filter(col("is_valid") === false)
  log.info(s"Task::Validate -> ${ Timer.stop() }s")


  Timer.start()
  fs.write(OUT_PATH_OK, dfOk.drop(dropColumns: _*))
  fs.write(OUT_PATH_ERR, dfErr.drop(dropColumns: _*))
  log.info(s"Task::Write -> ${ Timer.stop() }s")

}