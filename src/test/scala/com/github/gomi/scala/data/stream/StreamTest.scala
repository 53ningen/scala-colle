package com.github.gomi.scala.data.stream

import com.github.gomi.scala.data.stream
import com.github.gomi.scala.data.stream.Stream._

class StreamTest extends org.specs2.mutable.Specification {

  "Stream" should {

    "use toString Method" in {
      stream.Nil.toString mustEqual "Nil{}"
      cons(1, stream.Nil).toString mustEqual "Cons{1,Nil}"
      cons(1, cons(2, stream.Nil)).toString mustEqual "Cons{1,???}"
    }

    "toList" in {
      stream.Nil.toList mustEqual List()
      cons(1, stream.Nil).toList mustEqual List(1)
      cons(1, cons(2, stream.Nil)).toList mustEqual List(1, 2)
    }

    "fmap" in {
      stream.Nil.fmap(x => x).toList mustEqual List()
      cons(1, stream.Nil).fmap(x => 2 * x).toList mustEqual List(2)
      cons(1, cons(2, stream.Nil)).fmap(x => 2 * x).toList mustEqual List(2, 4)
      cons(1, cons(2, stream.Nil)).fmap(x => 2 * x).fmap(y => y.toString).toList mustEqual List("2", "4")
    }

  }

}
