package br.avcaliani.scala.app
import org.scalatest.FunSuite

class MainTest extends FunSuite {
  test("CubeCalculator.cube") {
    assert(Main.cube(3) === 27)
  }
}
