package br.avcaliani.scala.app.validator

import org.apache.spark.sql.{Column, DataFrame, SparkSession}
import org.apache.spark.sql.functions.when

/**
 * Class to validate USA User Names data.
 * @param ss {@link SparkSession}.
 */
class NameValidator(ss: SparkSession) extends Validator {
  import ss.implicits._

  override def validate(df: DataFrame): DataFrame = df
    .withColumn("gender_valid", checkGender($"gender"))
    .withColumn("number_valid", checkNumber($"number"))
    .withColumn("is_valid", isValid($"gender_valid", $"number_valid"))

  private def checkGender(gender: Column): Column =
    when(gender === "F" or gender === "M", true)
    .otherwise(false)
    .cast("boolean")

  private def checkNumber(number: Column): Column =
    when(number >= 1000, true)
    .otherwise(false)
    .cast("boolean")

  private def isValid(genderValid: Column, numberValid: Column): Column =
    when(genderValid and numberValid, true)
    .otherwise(false)
    .cast("boolean")
}
