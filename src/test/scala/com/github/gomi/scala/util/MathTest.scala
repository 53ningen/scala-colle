package com.github.gomi.scala.util

import org.specs2.mutable.Specification

class MathTest extends Specification {

  "MathTest" should {

    "fact" in {
      Math.fact(-1) mustEqual None
      Math.fact(0) mustEqual Some(1)
      Math.fact(1) mustEqual Some(1)
      Math.fact(2) mustEqual Some(2)
      Math.fact(3) mustEqual Some(6)
      Math.fact(4) mustEqual Some(24)
      Math.fact(5) mustEqual Some(120)
      Math.fact(6) mustEqual Some(720)
    }

    "fib" in {
      Math.fib(0) mustEqual Some(0)
      Math.fib(1) mustEqual Some(1)
      Math.fib(2) mustEqual Some(1)
      Math.fib(3) mustEqual Some(2)
      Math.fib(4) mustEqual Some(3)
      Math.fib(5) mustEqual Some(5)
      Math.fib(6) mustEqual Some(8)
      Math.fib(7) mustEqual Some(13)
      Math.fib(8) mustEqual Some(21)
      Math.fib(9) mustEqual Some(34)
      Math.fib(10) mustEqual Some(55)
      Math.fib(11) mustEqual Some(89)
      Math.fib(12) mustEqual Some(144)
    }

  }

}
