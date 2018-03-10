package com.First.scala

object A2_5 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val x = new Rational(1, 2)                      //> x  : com.First.scala.Rational = 1/2
  val y = new Rational(1, 2)                      //> y  : com.First.scala.Rational = 1/2
  println(x + y)                                  //> 1/1
  //new Rational(1,0)
  new Rational(9)                                 //> res0: com.First.scala.Rational = 9/1
  x < y                                           //> res1: Boolean = false

	//val x = sqrt(y)
	//assert(x>=0)
}

class Rational(x: Int, y: Int) {
	//require is define in pre def
  require(y != 0, "demon must be nonzero")

  //other constructor
  def this(x: Int) = this(x, 1)

  private def gcd(x: Int, y: Int): Int = if (x % y == 0) y else gcd(y, x % y)
  private val g = gcd(x, y)
  val numer = x / g
  val denom = y / g

  def +(s: Rational): Rational =
    new Rational(numer * s.denom + s.numer * denom, denom * s.denom)

  //unary -
  def nag: Rational = new Rational(-numer, denom)
  def unary_- : Rational = new Rational(-numer, denom)

  //def - (that:Rational) = this + (that.nag)
  def -(that: Rational) = this + -that

  def <(that: Rational) = this.numer * that.denom < that.numer * this.denom
  def max(that: Rational) = if (this < that) that else this

  override def toString = {
    numer + "/" + denom
  }

}