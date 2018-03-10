package week4.PatternMatching

object CaseClass {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  def eval(e:Expr):Int = e match{
  case number(n)=>n
  case sum(e1,e2)=>eval(e1) + eval(e2)
  }                                               //> eval: (e: week4.PatternMatching.Expr)Int
 	def show(e:Expr):String = e match{
  case number(n)=> n+""
  case sum(e1,e2)=>show(e1) + " + " + show(e2)
  case prod(e1,e2)=>show(e1) + " * " + show(e2)
  }                                               //> show: (e: week4.PatternMatching.Expr)String
  eval(sum(number(1),number(124)))                //> res0: Int = 125
  show(sum(number(1),number(124)))                //> res1: String = 1 + 124
  
  val res2 = 13::123::123::Nil                    //> res2  : List[Int] = List(13, 123, 123)
  Nil.::(3).::(2).::(1)                           //> res2: List[Int] = List(1, 2, 3)
  
  res2 match{
  case x::xs=>x
  }                                               //> res3: Int = 13
}
trait Expr{
};
case class number(n:Int) extends Expr;
case class sum(e1:Expr,e2:Expr) extends Expr;
case class prod(e1:Expr,e2:Expr) extends Expr;