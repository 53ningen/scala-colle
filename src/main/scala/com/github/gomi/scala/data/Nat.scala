package com.github.gomi.scala.data

import scala.language.higherKinds

sealed trait Nat

final class Zero extends Nat

final class Succ[N <: Nat] extends Nat

object Nat {

  type _0 = Zero
  type _1 = Succ[_0]
  type _2 = Succ[_1]
  type _3 = Succ[_2]
  type _4 = Succ[_3]
  type _5 = Succ[_4]
  type _6 = Succ[_5]
  type _7 = Succ[_6]
  type _8 = Succ[_7]
  type _9 = Succ[_8]

}

sealed trait ToInt[N <: Nat] {
  def apply(): Int
}

object ToInt {

  def apply[N <: Nat](implicit toInt: ToInt[N]): Int = toInt()

  implicit def zeroToInt = new ToInt[Zero] {
    def apply(): Int = 0
  }

  implicit def zeroToSucc[N <: Nat](implicit toInt: ToInt[N]) = new ToInt[Succ[N]] {
    def apply(): Int = toInt() + 1
  }

}
