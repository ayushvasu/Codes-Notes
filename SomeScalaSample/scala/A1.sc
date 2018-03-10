package com.First.scala

object A1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

	val q =12                                 //> q  : Int = 12
  def first_1(x: Int, y: => Int) = x              //> first_1: (x: Int, y: => Int)Int
  def first_2(x: Int, y: Int) = x                 //> first_2: (x: Int, y: Int)Int
  //def loop():Int=loop
  def fact(x: Int): Int = if (x < 2) 1 else x * fact(x - 1)
                                                  //> fact: (x: Int)Int
  first_1(12, fact(1234567))                      //> res0: Int = 12
  first_2(12, fact(6))                            //> res1: Int = 12
  //first_2(12, fact(1234))--------------->show error because first fact(1234) evaluates

  def abs(x: Double) = if (x >= 0) x else -x      //> abs: (x: Double)Double
  def And(x: Boolean, y: Boolean): Boolean = if (x) y else false
                                                  //> And: (x: Boolean, y: Boolean)Boolean
  def Or(x: Boolean, y: Boolean): Boolean = if (x) true else y
                                                  //> Or: (x: Boolean, y: Boolean)Boolean

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGood(guess, x)) guess
    else sqrtIter(improve(guess, x), x)           //> sqrtIter: (guess: Double, x: Double)Double

  def isGood(guess: Double, x: Double): Boolean =
    if (abs(guess * guess - x) <= .01) true else false
                                                  //> isGood: (guess: Double, x: Double)Boolean

  def improve(guess: Double, x: Double): Double =
    (guess + x / guess) / 2                       //> improve: (guess: Double, x: Double)Double

  def sqrt(x: Double) = sqrtIter(1D, x)           //> sqrt: (x: Double)Double

  sqrt(2)                                         //> res2: Double = 1.4166666666666665
  sqrt(4)                                         //> res3: Double = 2.000609756097561

  def sqrt2(x: Double): Double = {
    def sqrtIter(guess: Double): Double =
      if (isGood(guess)) guess else sqrtIter(improve(guess))
    def improve(guess: Double): Double =
      (guess + x / guess) / 2
    def isGood(guess: Double): Boolean =
      if (abs(guess * guess - x) / x <= .001) true else false

    sqrtIter(1.0)
  }                                               //> sqrt2: (x: Double)Double

  sqrt2(2)                                        //> res4: Double = 1.4142156862745097
  sqrt2(4)                                        //> res5: Double = 2.000609756097561
  sqrt2(10000)                                    //> res6: Double = 100.00714038711746
  sqrt2(1e-8)                                     //> res7: Double = 1.0000040611237676E-4
}