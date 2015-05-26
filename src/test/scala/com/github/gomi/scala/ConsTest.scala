package com.github.gomi.scala

import com.github.gomi.scala.Stream._

class ConsTest extends org.specs2.mutable.Specification {

  "Stream" should {

    "use toString Method" in {
      Nil.toString mustEqual "Nil{}"
      cons(1, Nil).toString mustEqual "Cons{1,Nil}"
      cons(1, cons(2, Nil)).toString mustEqual "Cons{1,???}"
    }

    "toList" in {
      Nil.toList mustEqual List()
      cons(1, Nil).toList mustEqual List(1)
      cons(1, cons(2, Nil)).toList mustEqual List(1, 2)
    }

  }

}
