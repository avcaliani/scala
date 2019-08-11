package br.avcaliani.scala.app.validator

import org.apache.spark.sql.DataFrame

/**
 * Validator Trait.
 */
trait Validator extends Serializable {

  /**
   * Validate data frame {@code df}.
   *
   * @param df {@link DataFrame}.
   * @return Updated {@link DataFrame}.
   */
  def validate(df: DataFrame): DataFrame

}
