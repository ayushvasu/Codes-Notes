class Util {
  val nanoSecsInMilliSec = 1000000L
  def profile[R](codeBlock: => R): (R, Long) = {
  		println("startstarte")
      val t0 = System.nanoTime()
      val result = codeBlock
      (result, (System.nanoTime() - t0)/nanoSecsInMilliSec)
  }
  
}



//Trait and Mixins
trait Friend {
	val name:String
	def listen = println(s"i am listening $name")
}


class Human(val name:String) extends Friend
class Dog(val name:String) extends Friend

def seekHelp(friend:Friend) = friend.listen

val sam = new Human("Sam")
seekHelp(sam)

val samTheDog = new Dog("SamDog")
seekHelp(samTheDog)

//deligating is nothing but the forwarding of the work

//part 2

class Animal(val name:String)
class Dog2(override val name:String) extends Animal(name) with Friend
val samTheDog = new Dog2("SamDog2")
seekHelp(samTheDog)


class Cat(override val name:String) extends Animal(name)
//instance level implimantation 
val alf = new Cat("Alf") with Friend
seekHelp(alf)


println(sam.getClass)
println(cat.getClass)
println(alf.getClass)



//chaining of trait 
trait Curious {
	def pokeAround = println("Curious--------") 
}



class Dog3(override val name:String) extends Animal(name) with Friend with Curious
val samTheDog2 = new Dog3("SamDog3")
seekHelp(samTheDog2)
samTheDog2.pokeAround
 

class Cat(override val name:String) extends Animal(name) with Curious
//instance level implimantation 
val alf = new Cat("Alf") with Friend
seekHelp(alf)
alf.pokeAround




//Chaining
abstract class Writer{
	def write(msg:String)
}

class StringWriter extends Writer{
	val target = new StringBuilder
	override def toString() = target.toString
	def write(msg:String) = target.append(msg)
}

//extends means this trait can be only use with the writer
//that this trait only be use on mixin a class which is writer
trait UppercaseFilter extends Writer{
	abstract override def write(msg:String) = 
		super.write(msg.toUpperCase())
			//super means object to my left
}

trait ReplaceFilter extends Writer{
	abstract override def write(msg:String) = 
		super.write(msg.replace("stupid","s********"))
}

def writeStuff(writer:Writer)={
	writer.write("this is stupid")
	println(writer)
}

writeStuff(new StringWriter)
writeStuff(new StringWriter with UppercaseFilter)
writeStuff(new StringWriter with UppercaseFilter with ReplaceFilter)
writeStuff(new StringWriter with ReplaceFilter)
//it will apply writer right to left

//scala> writeStuff(new StringWriter)
//this is stupid

//scala> writeStuff(new StringWriter with UppercaseFilter)
//THIS IS STUPID

//scala> writeStuff(new StringWriter with UppercaseFilter with ReplaceFilter)
//THIS IS S********

//scala> writeStuff(new StringWriter with ReplaceFilter)
//this is s********

//scala> writeStuff(new StringWriter with ReplaceFilter with UppercaseFilter)
//THIS IS STUPID------------------> because it works right to left


/*
Traits are compile time mixins
and Mixins are run time mixins
*/

//referential transparency
def add(op1:Int,op2:Int)=op1+op2
println(add(1,2))
//------>result=add(1,2)
println(add(1,2))//-----compiler will replace it with ----------> println(result)
println(add(1,2))//-----compiler will replace it with ----------> println(result)
println(add(1,2))//-----compiler will replace it with ----------> println(result)
//memoization-------> is like caching
/*
pure function
	if(result not already present)
		call the function
		store the result
	return result
*/



/*------------MONADS--------------*/
//monads is the way to put purity to language which is otherwise impure

import scala.io._

def getInput() ={
	println("put number")
	StdIn.readInt
}

lazy val input1 = getInput()
lazy val input2 = getInput()

if(Math.random() > 0.5)
	input2 * 1.0

println(input1 - input2)



//A monad makes the sequence explicit



def add(op1:Int ,op2 :Int) = op1+op2

val add4 = add(4,_:Int)//partial applied function

add4(7)


/*
A monad has -
  a type constructor m
  a chaining function that chains output of one function into another
*/

val f = (x:Int) => x+1
val f2 = (x:Int) => x * 2

val incAndDoubleIt = f andThen f2
println(incAndDoubleIt(5))

/*
various Monads
	mayBe-------> Some/None(Option)
	List
	IO
	Logger
*/

object IO{
	def |>[T](f:String => T) ={
		println("Enter a number")
		val input = StdIn.readLine
		f(input)
	}

}
IO |> {x => println(x)}



class Logger[T](val value : T){
	def |>[T2](f: T => T2) = {
		println(value)
		new Logger(f(value))
	}
}
object Logger{
	def apply[T](value: T) = new Logger(value)
}
def ignore[T](value : T) = {}
new Logger(2) |> {x => x+1} |> {x => x*2.2} |> {x => x-1} |> ignore


implicit def converterToLogger[T](value:T) = new Logger(value)
2 |> {x => x+1} |> {x => x*2.2} |> {x => x-1} |> ignore
