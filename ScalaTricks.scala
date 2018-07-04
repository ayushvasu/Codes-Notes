def get:Any = "ayush"

get.isInstanceOf[String] //true
get.isInstanceOf[Int] //false

get match{
      case _:String => print(res0+"string")
      }

(1,2) match {
      case (_:Int,_:Int) => println("good")
      }

(1,2) match {
      case _:(Int,Int) => println("better")
      }


abstract class MyAbstract
case class MyFirst() extends MyAbstract
case class MySecond() extends MyAbstract
val x: MyAbstract = MyFirst()
x match {
   case aOrB @ (MyFirst() | MySecond()) => doSomething(aOrB)
   case _ => doSomethingElse()
}
