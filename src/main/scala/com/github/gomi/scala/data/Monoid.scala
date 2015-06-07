package com.github.gomi.scala.data

trait Monoid[A] {

  def mappend(a1: A, a2: A): A

  def mzero: A

}

object Monoid {

  val intAddition: Monoid[Int] = new Monoid[Int] {
    override def mappend(a1: Int, a2: Int): Int = a1 + a2
    override def mzero: Int = 0
  }

  val intMultiplication: Monoid[Int] = new Monoid[Int] {
    override def mappend(a1: Int, a2: Int): Int = a1 * a2
    override def mzero: Int = 1
  }

  val stringMonoid: Monoid[String] = new Monoid[String] {
    override def mappend(a1: String, a2: String): String = a1 + a2
    override def mzero: String = ""
  }

  def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {
    override def mappend(a1: List[A], a2: List[A]): List[A] = a1 ++ a2
    override def mzero: List[A] = Nil
  }

  val booleanOr: Monoid[Boolean] = new Monoid[Boolean] {
    override def mappend(a1: Boolean, a2: Boolean): Boolean = a1 || a2
    override def mzero: Boolean = false
  }

  val booleanAnd: Monoid[Boolean] = new Monoid[Boolean] {
    override def mappend(a1: Boolean, a2: Boolean): Boolean = a1 && a2
    override def mzero: Boolean = true
  }

  def optionMonoid[A]: Monoid[Option[A]] = new Monoid[Option[A]] {
    override def mappend(a1: Option[A], a2: Option[A]): Option[A] = a1.orElse(a2)
    override def mzero: Option[A] = None
  }

  def dual[A](m: Monoid[A]): Monoid[A] = new Monoid[A] {
    override def mappend(a1: A, a2: A): A = m.mappend(a2, a1)
    override def mzero: A = m.mzero
  }

  def endo[A]: Monoid[A => A] = new Monoid[(A) => A] {
    override def mappend(a1: (A) => A, a2: (A) => A): (A) => A = a1.compose(a2)
    override def mzero: (A) => A = (a) => a
  }

}
