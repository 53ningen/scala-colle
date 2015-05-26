package com.github.gomi.scala

/**
 * Stream
 */
sealed trait Stream[+A] {

  def toList: List[A] = {
    def go(s: Stream[A], l: List[A]): List[A] = s match {
      case Cons(x, xs) => go(xs(), x() :: l)
      case _ => l
    }
    go(this, List()).reverse
  }

  def fmap[B](f: A => B): Stream[B] = this match {
    case Cons(x, xs) => Cons(() => f(x()), () => xs().fmap(f))
    case Nil => Nil
  }

}

case object Nil extends Stream[Nothing] {

  override def toString: String = "Nil{}"

}

case class Cons[+A](head: () => A, tail: () => Stream[A]) extends Stream[A] {

  override def toString: String = s"Cons{${head()},${if (tail().isInstanceOf[Cons[A]]) "???" else "Nil"}}"

}

object Stream {

  def cons[A](x: => A, xs: => Stream[A]): Stream[A] = {
    lazy val head = x
    lazy val tail = xs
    Cons(() => head, () => tail)
  }

}
