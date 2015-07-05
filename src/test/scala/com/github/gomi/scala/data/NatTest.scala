package com.github.gomi.scala.data

import com.github.gomi.scala.data.Nat._
import org.specs2.mutable.Specification

class NatTest extends Specification {

  "Nat" should {

    "natural number" in {
      ToInt[Zero] mustEqual 0
      ToInt[_0] mustEqual 0
      ToInt[Succ[Zero]] mustEqual 1
      ToInt[_1] mustEqual 1
      ToInt[Succ[Succ[Zero]]] mustEqual 2
      ToInt[_2] mustEqual 2
    }

  }

}
