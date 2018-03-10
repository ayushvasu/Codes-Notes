package week4.Exprs

trait Expr {
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}

class Number(n: Int) extends Expr {
  def numValue: Int = n
  def leftOp: Expr = throw new Error("Number.leftOp")
  def rightOp: Expr = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def numValue: Int = throw new Error("Sum.Value")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
}

class Prod(e1: Expr, e2: Expr) extends Expr {
  def numValue: Int = throw new Error("Number.leftOp")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
}


//---------Much Better solution would be 

trait Expr2 {
  def eval :Int
}

class Number2(n: Int) extends Expr2 {
  def eval :Int = n
}

class Sum2(e1: Expr2, e2: Expr2) extends Expr2 {
  def eval :Int = e1.eval + e2.eval
}
class prod2(e1: Expr2, e2: Expr2) extends Expr2 {
  def eval :Int = e1.eval * e2.eval
}