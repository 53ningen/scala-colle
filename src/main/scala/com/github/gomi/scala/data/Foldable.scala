package com.github.gomi.scala.data

trait Foldable[F[_]] {

  import Monoid._

  def foldRight[A, B](as: F[A])(z: B)(f: (A, B) => B): B = {
    foldMap(as)(f.curried)(endo)(z)
  }

  def foldLeft[A, B](as: F[A])(z: B)(f: (A, B) => B): B = {
    foldMap(as)(f.curried)(dual(endo))(z)
  }

  def foldMap[A, B](as: F[A])(f: A => B)(mb: Monoid[B]): B = {
    foldRight(as)(mb.mzero)((a, b) => mb.mappend(f(a), b))
  }

}
