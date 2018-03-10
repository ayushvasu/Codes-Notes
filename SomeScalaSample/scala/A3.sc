package com.First.scala

object A3 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def fact(f: Int): Int = if (f <= 1) 1 else f * fact(f - 1)
                                                  //> fact: (f: Int)Int
  
  
  def sumInt(a:Int,b:Int):Int={
  def fun(acc:Int,a:Int):Int=
  	if(a>b)acc else fun(acc+a,a+1)
  fun(0,a)
  }                                               //> sumInt: (a: Int, b: Int)Int
  
  sumInt(1,10)                                    //> res0: Int = 55
  
  def sum(f:Int=>Int,a:Int,b:Int):Int={
  if(a>b) 0 else f(a)+sum(f,a+1,b)
  }                                               //> sum: (f: Int => Int, a: Int, b: Int)Int
  
  def sumInt2(f:Int=>Int,a:Int,b:Int):Int={
  def fun(acc:Int,a:Int):Int=
  	if(a>b)acc else fun(acc+f(a),a+1)
  fun(0,a)
  }                                               //> sumInt2: (f: Int => Int, a: Int, b: Int)Int
  sumInt2(fact,2,4)                               //> res1: Int = 32
  sumInt2(x=>x*x*x,2,3)                           //> res2: Int = 35
}