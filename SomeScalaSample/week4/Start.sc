package week4

object Start {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
                                                  //> singleton: [T](elem: T)week4.Cons[T]
  singleton[Int](1)                               //> res0: week4.Cons[Int] = {1 {Nil}}

  def nth[T](n: Int, xs: List[T]): T =
    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) xs.head
    else nth(n - 1, xs.tail)                      //> nth: [T](n: Int, xs: week4.List[T])T

  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : week4.Cons[Int] = {1 {2 {3 {Nil}}}}
	
	//Throw Error IndexOutOfBoundsException
  //nth(20, list)
  nth[Int](2,list)                                //> res1: Int = 3
	val as=  List(1,2)                        //> as  : week4.Cons[Int] = {1 {2 {Nil}}}
}