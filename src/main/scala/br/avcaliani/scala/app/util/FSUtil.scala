package br.avcaliani.scala.app.util

import java.util.UUID

import br.avcaliani.scala.app.Props
import org.apache.hadoop.fs.{FileSystem, FileUtil, Path}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * File System Util.
 *
 * @param ss {@link SparkSession}.
 * @param fs {@link FileSystem}.
 */
class FSUtil(ss: SparkSession, fs: FileSystem) extends Serializable with Props {

  val tmpDir = s"${ getProperty(s"hdfs.output.tmp") }/${ UUID.randomUUID().toString }"

  /**
   * Read file data and put it into a {@link DataFrame} structure.
   *
   * @param path Entry File Path.
   * @param delimiter Optional Delimiter.
   * @return {@link DataFrame}.
   */
  def read(path: String, delimiter: String = ","): DataFrame = ss.read
      .option("header", "true")
      .option("delimiter", delimiter)
      .csv(path)

  /**
   * Write {@link DataFrame} into a file.
   *
   * @param path Output File Path.
   * @param df {@link DataFrame}.
   * @param delimiter Optional Delimiter.
   * @return {@code true} or {@code false}
   */
  def write(path: String, df: DataFrame, delimiter: String = ","): Boolean = {
      df
      .coalesce(1)
      .write
      .mode(SaveMode.Overwrite)
      .option("header", "true")
      .option("delimiter", delimiter)
      .csv(tmpDir)

    val tmp = new Path(tmpDir)
    val dst = new Path(path)
    if (fs.exists(dst))
      fs.delete(dst, true)

    FileUtil.copyMerge(
      fs, tmp, fs, dst, true, ss.sparkContext.hadoopConfiguration, null
    )
  }

}
