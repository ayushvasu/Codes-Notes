#Reactive Programing Principle 

#Part 1 Basics of scala

Reactive application

event driven - react to event
scalable     - react to load
resilient	 - react to failures
responsive	 - react to users

Event Driven 
	Traditionally - Systems are composed of multiple threads which communicate with shared, synchronized state.
		> strong coupling, hard to compose

	Now - System are composed from loosely coupled event handlers
		> Event can be handle asynchronously, without blocaking

Scalable

	A application is scalable if it is able to expended according to its usage.

		> Scale Up : make use of parellelism in multi core system
		> Scale Out: make use of multiple server nodes

	** Important for scalability : Minimize shared mutable state
	** Important for scale out : Location transparency, resilience 

Resilient 

	An application is resilient if it can recover quickly from failures.
	Failure can be :
	1 s/w failure
	2 H/W
	3 connection failure

Typically resilience can not be added as an afterthought; it needs to be part of the design from the beginning.

Needed :
	Loose coupling
	strong encapsulation of state
	prevasive supervisor hierarchies

Responsive :

	An apllication is responsive if it provides rich real time interaction with its users even under load and in the presence of failures.

	Responsive application can be built on event driven scalable and resilient architecture.



Call backs (Java Observers Pattern)

	You register your self to call back here.............

	class counter extends ActionListener {
		private var count = 0
		
		button.addActionListener(this)

		def actionPerformed(e: ActionEvent): Unit ={
			count += 1
		}
	}


Problem 

> needs shared mutable state
> can not be composed
> leads quickly to "call back hell"


How to do better

Use fundamental constructions from functional programing -
	...to get composable event abstractions.
		> Event are first class
		> Event are often represent as message
		> Handlers of event are also first class
		> Complex handlers can be composed from primitive ones



------------------------------------------------
|Abstraction over events : futures 			   |
|Abstraction over events streams : observables |
|Message passing architecture : Actors 		   |
------------------------------------------------


Use case class to define complex data.

abstract class Json
case class JSeq(elems: List[JSON])			extends JSON
case class JObj(binding: Map[String, JSON])	extends JSON
case class JNum(num: Double)				extends JSON
case class JStr(str: String)				extends JSON
case class JBool(b: Boolean)				extends JSON
case object JNull							extends JSON


def show(json: JSON):String = json match {
	case JSeq(elems) =>
		"[" + (elems map show mkString ",") + "]"
	case JObj(bindings) =>
	 val assocs = binding map {
	 	case (key, value) => "\"" + key + "\" : \"" + show(value)
	 }
	 "{" + (assocs mkString ", ") + "}"
	case JNum(num) => num.toString
	case JNum(num) => '\"' + str + '\"'
	case JNum(num) => b.toString
	case JNull =>	"null"
}


Case Blocks

	Question : Whats the type os 

	{ case (key, value) => "\"" + key + "\" : \"" + show(value) }

	type JBinding = (String, JSON)

	type of function 

		scala.Function1[JBinding, String]


	trait Map[Key, Value] extends (Key => Value)

	trait Seq[Elem] extends (Int => Elem)


Partial Matches

	val fun: String => String = {case "ping" => "pong"}

Partial Functions 

	val fun: PartialFunction[String, String] = {
	case "ping" => "pong"
	}

	f.isDefinedAt("ping") ===> true
	f.isDefinedAt("ayush") ===> false


----------------------------------------------------------------------------------------------------------
Collection

						iterable
		Seq 				Set 				Map

IndexSeq | LinearSeq
Vector	 | List


abstract class List[+T] {
	def map[U](f: T=>U):List[U] = this match {
		case x :: xs => f(x) :: xs.map(f)
		case Nil => Nil
	}
	def flatMap[U](f: T=>List[U]):List[U] = this match {
		case x :: xs => f(x) ++ xs.flatMap(f)
		case Nil => Nil
	}
	def filter(p: T => Boolean): List[T] = this match {
	case x :: xs =>
		if (p(x)) x :: xs.filter(p) else xs.filter(p)
	case Nil => Nil
	}
}

----------------------------------------------------------------------------------------------------------
Random Value Generator 


trait Generator[+T] {
	def generate: T
}

val integer = new Generator[Int] {
	val rand = new java.util.Random
	def generate = rand.nextInt()
}

val boolean = new Generator[Boolean] {
	def generate = integer.generate > 0
}

val pairs = new Generator[(Int, Int)] {
	def generate = (integer.generate, integer.generate)
}

//can convert to 

trait Generator[+T] {
	self =>      //an alias for "this"

	def generate: T

	def map[S](f: T => S): Generator[S] = new Generator[S] {
		def generate = f(self.generate)   // announimous class can not use "this"
	}

	def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
		def generate = f(self.generate).generate
	}
}

val boolean = for(x <- integer) yield x > 0
//expends to
val boolean = integers map {x=> x>0}
val boolean = new Generator[Boolean] {
	def generate = (x: Int => x > 0)(integer.generate)
}

-------------------------------------------------------


def pairs[T, U](t: Generator[T], u: Generator[U]) = for {
	x <- t
	y <- u
} yield (x, y)

//expands to 


def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
	x => u map { y => (x, y) } }
}

def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
		x = new Generator[(T, U)] {def generate = (x, u.generate)} 
	}
}

def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)] {
	def generate = (new Generator[(T, U)]
		def generate = (t.generatexl, u.generate)}
	).generate 
}

-------------------------Generator Example (Building Blocks)---------------------------
def single[T](x: T):Generator[T] = new Generator[T]{
	def generate = x
}

def choose(lo: Int,hi: Int):Generator[Int] = 
	for(x <- integer) yield lo + x % (hi - lo)

def oneOf[T](xs: T*): Generator[T] =
	for(idx <- choose(0,xs.length)) yield xs(idx)


----------------------List---------------------------------
def lists: Generator[List[Int]] = for {
	isEmpty <- boolean
	list <- if(isEmpty) emptyList else nonEmptyList
} yield list

def emptyList = singlt(Nil)

def nonEmptyList = for {
	head <- integer
	tail <- lists
} yield head :: tail


--------------Tree----------------
trait Tree

case class Inner(left: Tree, right: Tree) extends Tree

case class Leaf(x: Int) extends Tree

def leaf: Generator[Leaf] = for {
	x <- integer
} yield Leaf(x)

def inners: Generator[Inner] = for {
	l <- tree
	r <- tree
} yield Inner(l,r)

def tree:Generator[Tree] = for {
	isLeaf <- boolean
	tree <- if(isLeaf) leaf else inners
} yield tree

===========================================================================================================================================================================================================

Monads

Data Structure with map and flatMap seems to be quite common.

In fact there's a name that describes this class of a data structures to gether with some algebraic laws that they should have.

They are called monads.

A monad is a parametric type M[T] with two operations, flatMap and unit that have to satisfy some laws...

trait M[T] {
	def flatMap[U](f: T => M[U]) : M[U]
}
def unit[T](x: T) : M[T]

In the literature, flatMap is more commonly called bind.


Example of Monads
> List is the monad
> Set is the monad
> Option is the monad
> Generator is the monad

** map can be defined for every monad as a combination of flatMap and unit :
	m map f == m flatMap (x => unit(f(x)))
			== m flatMap (f andThen unit)

Monads Laws

Associated
Left unit 
Right uint
