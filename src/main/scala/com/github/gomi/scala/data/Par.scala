package com.github.gomi.scala.data

import java.util.concurrent.{Callable, TimeUnit, Future, ExecutorService}

object Par {

  type Par[A] = ExecutorService => Future[A]

  /*
   * primitives
   */
  def unit[A](a: A): Par[A] = (es: ExecutorService) => UnitFuture(a)

  private case class UnitFuture[A](get: A) extends Future[A] {

    override def isDone: Boolean = true

    override def get(timeout: Long, units: TimeUnit): A = get

    override def isCancelled: Boolean = false

    override def cancel(mayInterruptIfRunning: Boolean): Boolean = false

  }

  def fork[A](a: => Par[A]): Par[A] =
    (es: ExecutorService) => {
      es.submit(new Callable[A] {
        override def call(): A = a(es).get
      })
    }

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] =
    (es: ExecutorService) => {
      val af = a(es)
      val bf = b(es)
      UnitFuture(f(af.get, bf.get))
    }

  def flatMap[A, B](pa: Par[A])(f: A => Par[B]): Par[B] =
    (es: ExecutorService) => {
      val a: A = run(es)(pa).get
      run(es)(f(a))
    }

  def run[A](es: ExecutorService)(a: Par[A]): Future[A] = a(es)

  /*
   * derivative-combinators
   */
  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def asyncF[A, B](f: A => B): A => Par[B] = a => lazyUnit(f(a))

  def map[A, B](par: Par[A])(f: A => B): Par[B] =
    map2(par, unit(()))((a, _) => f(a))

  def sequence[A](ps: List[Par[A]]): Par[List[A]] =
    ps.foldRight[Par[List[A]]](unit(List()))((p, pl) => map2(p, pl)(_ :: _))

  def parMap[A, B](as: List[A])(f: A => B): Par[List[B]] = fork {
    sequence(as.map(asyncF(f)))
  }

  def parFilter[A](as: List[A])(f: A => Boolean): Par[List[A]] = {
    val l: List[Par[List[A]]] = as.map(asyncF((a: A) => if (f(a)) List(a) else List()))
    map(sequence(l))(_.flatten)
  }

  def sum(ints: IndexedSeq[Int]): Par[Int] =
    if (ints.size <= 1) Par.unit(ints.headOption.getOrElse(0))
    else {
      val (l, r) = ints.splitAt(ints.size / 2)
      Par.map2(fork(sum(l)), fork(sum(r)))(_ + _)
    }

  def sortPar(parList: Par[List[Int]]): Par[List[Int]] =
    map(parList)(_.sorted)

}
