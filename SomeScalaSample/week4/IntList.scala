package week4

trait List[+T] {
  def isEmpty:Boolean
  def head: T
  def tail: List[T]
  //def prepend(elem:T):List[T] = new Cons(elem,this)
  def prepend[U>:T](elem:U):List[U] = new Cons(elem,this)
}

class Cons[T](val head:T,val tail: List[T])extends List[T]{
  def isEmpty = false
  override def toString = s"{$head $tail}"
}

object Nil extends List[Nothing]{
  def isEmpty = true
  def head:Nothing = throw new NoSuchElementException("NilHead")
  def tail:Nothing = throw new NoSuchElementException("NilHead")
  override def toString = "{Nil}"
}

object List {
  def apply[T](x1:T,x2:T): Cons[T] = new Cons(x1,new Cons(x2 , Nil))
  def apply[T](): List[T] =  Nil
  def apply[T](x1:T): Cons[T] = new Cons(x1 , Nil)
}