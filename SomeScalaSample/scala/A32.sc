import math.abs
object A32 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val tolerance = 0.001                           //> tolerance  : Double = 0.001
  def isCloseEnough(x:Double,y:Double)=abs((x-y)/x)/x<tolerance
                                                  //> isCloseEnough: (x: Double, y: Double)Boolean
  def fixedPoint(f:Double=>Double)(firstGuess:Double)={
  	def loop(guess:Double):Double={
  	val next =f(guess)
  	if(isCloseEnough(guess,next)) next else loop(next)
  	}
  	loop(firstGuess)
  }                                               //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double
  
  fixedPoint(x=>1+x/2)(1)                         //> res0: Double = 1.99609375

  def sqrt(x:Double) = fixedPoint(y=>x/y)(1.0)    //> sqrt: (x: Double)Double
  //sqrt(2)
  
  def sqrt2(x:Double) =
  	fixedPoint(y=>(y + (x / y))/2)(1.0)       //> sqrt2: (x: Double)Double
  	
  sqrt2(3)                                        //> res1: Double = 1.7320508100147274
  
  def averageDamp(f:Double=>Double)(x:Double) = (x+f(x))/2
                                                  //> averageDamp: (f: Double => Double)(x: Double)Double
  
  def sqrt_2(x:Double)={
  fixedPoint(averageDamp(y=>x/y))(1.0)
  }                                               //> sqrt_2: (x: Double)Double
  
  sqrt_2(2)                                       //> res2: Double = 1.4142135623746899
}