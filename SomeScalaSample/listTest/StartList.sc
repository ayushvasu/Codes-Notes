package com.listTest

import java.util.ComparableTimSort
import scala.math.Ordered

object StartList {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def isort(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case x :: ys => insert(x, isort(ys))
  }                                               //> isort: (xs: List[Int])List[Int]
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case Nil => List(x)
    case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
  }                                               //> insert: (x: Int, xs: List[Int])List[Int]

  isort(List(9, 7, 5, 8, 3, 6, 8))                //> res0: List[Int] = List(3, 5, 6, 7, 8, 8, 9)

  List(9, 7, 5, 8, 3, 6, 8) updated (1, 23)       //> res1: List[Int] = List(9, 23, 5, 8, 3, 6, 8)

  def msort(xs: List[Int]): List[Int] = {
    if (xs.length < 2) xs
    else {
      val (f, s) = xs.splitAt(xs.length / 2)
      merge(msort(f), msort(s))
    }
  }                                               //> msort: (xs: List[Int])List[Int]

  def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
    case (Nil, Nil) => List()
    case (Nil, x2) => x2
    case (x1, Nil) => x1
    case (x :: x1, y :: y1) => if (y < x) y :: merge(xs, y1) else x :: merge(x1, ys)
  }                                               //> merge: (xs: List[Int], ys: List[Int])List[Int]

  msort(List(9, 7, 5, 8, 3, 6, 8))                //> res2: List[Int] = List(3, 5, 6, 7, 8, 8, 9)

  def qsort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    if (xs.length < 2) xs
    else {
      val head = xs.head
      val (f, s) = xs.tail.partition { x => lt(x, head) }
      qsort(f)(lt) ::: (head :: qsort(s)(lt))
    }
  }                                               //> qsort: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]
  qsort(List(9, 7, 5, 8, 3, 6, 8))(_ < _)         //> res3: List[Int] = List(3, 5, 6, 7, 8, 8, 9)

  def qsort2[T](xs: List[T])(ord: Ordering[T]): List[T] = {
    if (xs.length < 2) xs
    else {
      val head = xs.head
      val (f, s) = xs.tail.partition { x => ord.lt(x, head) }
      qsort2(f)(ord) ::: (head :: qsort2(s)(ord))
    }
  }                                               //> qsort2: [T](xs: List[T])(ord: Ordering[T])List[T]
  qsort2(List(9, 7, 5, 8, 3, 6, 8))(Ordering.Int) //> res4: List[Int] = List(3, 5, 6, 7, 8, 8, 9)
  def qsort3[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    if (xs.length < 2) xs
    else {
      val head = xs.head
      val (f, s) = xs.tail.partition { x => ord.lt(x, head) }
      qsort3(f) ::: (head :: qsort3(s))
    }
  }                                               //> qsort3: [T](xs: List[T])(implicit ord: Ordering[T])List[T]
  qsort3(List(9, 7, 5, 8, 3, 6, 8))               //> res5: List[Int] = List(3, 5, 6, 7, 8, 8, 9)
}