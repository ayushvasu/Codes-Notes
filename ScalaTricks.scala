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



implicit class IntOps(private val n: Int) extends AnyVal {
  def stars = "*" * n
}

5.stars // Equivalent to a static method call, no implicit conversion actually takes place


/*Whenever possible, use private[this] instead of private and final val instead of val as they enable the Scala compiler and the 
JVM to perform additional optimizations: direct field access vs. accessor method, or inlined constant vs. field access, respectively. 
(If final val surprised you, remember that it is not redundant, because in Scala final means "cannot be overridden", 
while in Java it may mean both that as well as "cannot be reassigned").*/



/*import System.{currentTimeMillis => now} or import System.{nanoTime => now} are very useful to have around.*/


/*You can use the following syntax to pass a sequence as a parameter to a variable length argument list method:*/

def foo(args: Int*) = ???

val seq = Seq(1, 2, 3)

foo(seq: _*)


/*There are some really neat tricks we can do with pattern matching:*/

val tuple = ("foo", 1, false)
val (x, y, z) = tuple
assert((x, y, z) == (("foo", 1, false)))

case class Foo(x: String, y: Int)
val foo = Foo("foo", 1)
val Foo(a, b) = foo
assert((a, b) == (("foo", 1)))

val seq = Seq(1, 2, 3, 4, 5, 6)
val x :: xs = seq
assert((x, xs) == ((1, Seq(2, 3, 4, 5, 6))))

// Same as above
val Seq(y, ys@_*) = seq
assert((y, ys) == ((1, Seq(2, 3, 4, 5, 6))))

// Skipping elements
val _ :: a :: b :: _ :: zs = seq
assert((a, b, zs) == ((2, 3, Seq(5, 6))))

// Works with other collections, too
val vector = Vector(1, 2, 3, 4, 5, 6)
val Vector(_, a, b, _, ws@_*) = vector
assert((a, b, ws) == ((2, 3, Vector(5, 6))))

val Array(key, value) = "key:value".split(':')

// Regular expressions
val regex = """(.)(.)(.)""".r
val regex(a, b, c) = "xyz" // Matches and extracts regex against "xyz"
assert((a, b, c) == (("x", "y", "z")))


val votes = Seq(("scala", 1), ("java", 4), ("scala", 10), ("scala", 1), ("python", 10))
val orderedVotes = votes
  .groupBy(_._1)
  .map { case (which, counts) => 
    (which, counts.foldLeft(0)(_ + _._2))
  }.toSeq
  .sortBy(_._2)
  .reverse

//---------------------
val list: java.util.List[Int] = Seq(1,2,3,4).asJava
val buffer: scala.collection.mutable.Buffer[Int] = list.asScala



//---------------------
def collect(results: List[Result] = Nil): Future[List[Result]] =
  doRpc() flatMap { result =>
    if (results.length < 9)
      collect(result :: results)
    else
      Future.value(result :: results)
  }

collect() onSuccess { results =>
  printf("Got results %s\n", results.mkString(", "))
}



//----------------------------

def divide(x: Int, y: Int): Future[Result] = {
  if (y == 0)
    return Future.exception(new IllegalArgumentException("Divisor is 0"))

  Future.value(x/y)
}




lazy val field = computation()
//V
//|
//| is (roughly) short-hand for
//|
//V
var _theField = None
def field = if (_theField.isDefined) _theField.get else {
  _theField = Some(computation())
  _theField.get
}


class SSLConnector(mkEngine: () => SSLEngine)
