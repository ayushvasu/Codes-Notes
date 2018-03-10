package week4.Exprs

object Start {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
 /* def eval(e: Expr): Int = {

    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else throw new Error("Unknown Expression " + e)
  }
  eval(new Sum(new Number(5), new Number(6)))
*/
  def eval2(e: Expr): Int = {
    if (e.isInstanceOf[Number]) e.numValue
    else if (e.isInstanceOf[Sum]) eval2(e.asInstanceOf[Sum].leftOp) + eval2(e.asInstanceOf[Sum].rightOp)
    else throw new Error("Unknown Expression " + e)
  }                                               //> eval2: (e: week4.Exprs.Expr)Int
  
  eval2(new Sum(new Number(5), new Number(6)))    //> res0: Int = 11
  
  new Sum2(new Number2(5), new Number2(6)).eval   //> res1: Int = 11
  new prod2(new Number2(5), new Number2(6)).eval  //> res2: Int = 11
}