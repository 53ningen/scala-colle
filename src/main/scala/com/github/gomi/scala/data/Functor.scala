package com.github.gomi.scala.data

trait Functor[F[_]] { self =>

  def fmap[A, B](fa: F[A])(f: A => B): F[B]

}
