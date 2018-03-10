package com.First.scala

object A2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val e = 123                                     //> e  : Int = 123

  def gcd(x: Int, y: Int): Int = if (x % y == 0) y else gcd(y, x % y)
                                                  //> gcd: (x: Int, y: Int)Int
  gcd(14, 21)                                     //> res0: Int = 7

  def fact(f: Int): Int = if (f <= 1) 1 else f * fact(f - 1)
                                                  //> fact: (f: Int)Int
  fact(12)                                        //> res1: Int = 479001600

	def factorial(x:Int):Int = {
	def fact_loop(acc:Int,n:Int):Int=
	if(n==0) acc else fact_loop(acc*n,n-1)
	
	fact_loop(1,x)
	}                                         //> factorial: (x: Int)Int
	factorial(12)                             //> res2: Int = 479001600
	
	val a= if(true) 1 else false              //> a  : AnyVal = 1
	val b = if(true) 1 else "ayush"           //> b  : Any = 1

}