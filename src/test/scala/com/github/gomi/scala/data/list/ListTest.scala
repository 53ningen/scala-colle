package com.github.gomi.scala.data.list

import org.specs2.mutable.Specification

class ListTest extends Specification {

  "List" should {

    "call #length" in {
      List.length(List()) mustEqual 0
      List.length(List(1)) mustEqual 1
      List.length(List("a", "b")) mustEqual 2
      List.length(List(1d, 2d, 3d)) mustEqual 3
    }

    "call #fmap" in {
      List.fmap(List())(x => x.toString) mustEqual List()
      List.fmap(List(1))(x => x.toString) mustEqual List("1")
      List.fmap(List(1d, 2d))(x => x.toString) mustEqual List("1.0", "2.0")
      List.fmap(List(1, 2, 3))(x => x.toString) mustEqual List("1", "2", "3")
      List(1, 2, 3, 4).fmap(x => x + 0.1) mustEqual List(1.1, 2.1, 3.1, 4.1)
    }

    "call #reverse" in {
      List.reverse(List()) mustEqual List()
      List.reverse(List(1)) mustEqual List(1)
      List.reverse(List(1d, 2d)) mustEqual List(2d, 1d)
      List.reverse(List(1, 2, 3)) mustEqual List(3d, 2d, 1d)
      List(1, 2, 3, 4).reverse mustEqual List(4, 3, 2, 1)
    }

    "call #filter" in {
      List.filter(List[Int]())(x => x > 2) mustEqual List()
      List.filter(List(1))(x => x > 2) mustEqual List()
      List.filter(List(1d, 2d))(x => x > 2) mustEqual List()
      List.filter(List(1, 2, 3))(x => x > 2) mustEqual List(3)
      List(1, 2, 3, 4).filter(x => x > 2) mustEqual List(3, 4)
    }

  }

}
