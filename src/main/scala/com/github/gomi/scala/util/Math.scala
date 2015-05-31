package com.github.gomi.scala.util

import scala.annotation.tailrec

object Math {

  def fact(n: BigInt): Option[BigInt] = {
    @tailrec
    def go(n: BigInt, buf: BigInt): BigInt = {
      if (n < BigInt(2)) buf
      else go(n - 1, n * buf)
    }

    if (n < BigInt(0)) None
    else if (n < BigInt(2)) Some(1)
    else Some(go(n - 1, n))
  }

  // todo: memorize
  def fib(n: BigInt): Option[BigInt] = {
    @tailrec
    def go(a: BigInt, b: BigInt, n: BigInt): BigInt = {
      if (n == BigInt(0)) a + b
      else go(b, a + b, n - 1)
    }

    if (n < BigInt(0)) None
    else if (n < BigInt(2)) Some(n)
    else Some(go(BigInt(0), BigInt(1), n - 2))
  }

}
