package com.listTest

object StartFuns {
  val lst = List(9, 7, 5, 8, 3, 6, 8)             //> lst  : List[Int] = List(9, 7, 5, 8, 3, 6, 8)

  lst filter (_ % 2 > 0)                          //> res0: List[Int] = List(9, 7, 5, 3)
  lst filterNot (_ % 2 > 0)                       //> res1: List[Int] = List(8, 6, 8)
  lst partition (_ % 2 > 0)                       //> res2: (List[Int], List[Int]) = (List(9, 7, 5, 3),List(8, 6, 8))

  lst takeWhile (_ % 2 > 0)                       //> res3: List[Int] = List(9, 7, 5)
  lst dropWhile (_ % 2 > 0)                       //> res4: List[Int] = List(8, 3, 6, 8)
  lst span (_ % 2 > 0)                            //> res5: (List[Int], List[Int]) = (List(9, 7, 5),List(8, 3, 6, 8))

  val data = List("a", "a", "a", "a", "as", "as", "b")
                                                  //> data  : List[String] = List(a, a, a, a, as, as, b)

  def pack[T](lst: List[T]): List[List[T]] = lst match {
    case Nil => Nil
    case x :: xs1 =>
      val (fis, sec) = lst partition (_ == x)
      fis :: pack(sec)
  }                                               //> pack: [T](lst: List[T])List[List[T]]

  pack(data)                                      //> res6: List[List[String]] = List(List(a, a, a, a), List(as, as), List(b))

  def packCount[T](lst: List[T]): List[(T, Int)] = {
    pack(lst) map (ys => (ys.head, ys.length))
  }                                               //> packCount: [T](lst: List[T])List[(T, Int)]
  packCount(data)                                 //> res7: List[(String, Int)] = List((a,4), (as,2), (b,1))

  def sum(xs: List[Int]) = (0 :: xs) reduceLeft (_ + _)
                                                  //> sum: (xs: List[Int])Int
  def product(xs: List[Int]) = (1 :: xs) reduceLeft (_ * _)
                                                  //> product: (xs: List[Int])Int

  def sum2(xs: List[Int]) = (xs foldLeft 0)(_ + _)//> sum2: (xs: List[Int])Int
  def product2(xs: List[Int]) = (xs foldLeft 1)(_ * _)
                                                  //> product2: (xs: List[Int])Int

}