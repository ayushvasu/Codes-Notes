package com.First.scala

object currying {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  def fact(f: Int): Int = if (f <= 1) 1 else f * fact(f - 1)
                                                  //> fact: (f: Int)Int
  def sumf(f: Int => Int): (Int, Int) => Int = {
    def suml(a: Int, b: Int): Int =
      if (a > b) 0 else f(a) + suml(a + 1, b)
    suml
  }                                               //> sumf: (f: Int => Int)(Int, Int) => Int

  def sumfact = sumf(fact)                        //> sumfact: => (Int, Int) => Int
  sumfact(2, 3)                                   //> res0: Int = 8

  sumf(fact)(2, 4)                                //> res1: Int = 32

  def sum_syn2(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum_syn2(f)(a + 1, b)//> sum_syn2: (f: Int => Int)(a: Int, b: Int)Int

  sum_syn2(fact)(1, 6)                            //> res2: Int = 873

  def product_syn(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1 else f(a) * product_syn(f)(a + 1, b)
                                                  //> product_syn: (f: Int => Int)(a: Int, b: Int)Int

  product_syn(x => x)(1, 4)                       //> res3: Int = 24

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }                                               //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:
                                                  //|  Int)Int
  def product_syn2(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
                                                  //> product_syn2: (f: Int => Int)(a: Int, b: Int)Int

	def fact_2(x:Int)=product_syn2(x=>x)(1,x) //> fact_2: (x: Int)Int
	fact_2(3)                                 //> res4: Int = 6
}