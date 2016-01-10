package com.github.gomi.scala.data.state

trait RandomGenerator {

  def nextInt: (Int, RandomGenerator)

}

case class SimpleRandomGenerator(seed: Long) extends RandomGenerator {

  def nextInt: (Int, RandomGenerator) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextGenerator = SimpleRandomGenerator(newSeed)
    ((newSeed >>> 16).toInt, nextGenerator)
  }

}

object RandomGenerator {

  type Rand[+A] = RandomGenerator => (A, RandomGenerator)

  def int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] = rng => (a, rng)

  def map[A, B](r: Rand[A])(f: A => B): Rand[B] = rng => {
    val (a1, r1) = r(rng)
    (f(a1), r1)
  }

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = rng => {
    val (a, r1) = ra(rng)
    val (b, r2) = rb(r1)
    (f(a, b), r2)
  }

  def both[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] = map2(ra, rb)((_, _))

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] =
    fs.foldRight(unit(List[A]()))((f, acc) => map2(f, acc)(_ :: _))

  def flatMap[A, B](r: Rand[A])(f: A => Rand[B]): Rand[B] = rng => {
    val (a1, rng1) = r(rng)
    f(a1)(rng1)
  }

  def nextIntPair: Rand[(Int, Int)] = both(int, int)

  def nonNegativeInt: Rand[Int] = map(int)(i => if (i < 0) -(i + 1) else i)

  def double: Rand[Double] = map(nonNegativeInt)(i => if (i == 0) i else i.toDouble / Int.MaxValue)

  def intDouble: Rand[(Int, Double)] = both(int, double)

}
