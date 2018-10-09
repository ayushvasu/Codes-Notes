#Scala Variance

	Variance defines Inheritance relationships of Parameterized Types. Variance is all about Sub-Typing.

	T is known as “Type Parameter” and List[T] is known as Generic.

	For List[T], if we use List[Int], List[AnyVal], etc. then these List[Int] and List[AnyVal] are known as “Parameterized Types”

	Variance defines Inheritance relationship between these Parameterized Types.


	The main advantage of Scala Variance is:

		Variance makes Scala collections more Type-Safe.
		Variance gives more flexible development.
		Scala Variance gives us a technique to develop Reliable Applications.


Scala supports the following three kinds of Variance.

> Covariant
> Invariant
> Contravariant

Covariant in scala
	
	If “S” is subtype of “T” then List[S] is is a subtype of List[T].

	This kind of Inheritance Relationship between two Parameterized Types is known as “Covariant”

	T <- S
	List[T] <- List[S]


	To represent Covariance relationship between two Parameterized Types, Scala uses the following syntax:
	
	Ex : List[+T]

	Prefixing Type Parameter with “+” symbol defines Covariance in Scala.


	class Animal[+T](val animial:T)
	class Dog
	class Puppy extends Dog
	class AnimalCarer(val dog:Animal[Dog])

	object ScalaCovarianceTest{
	  def main(args: Array[String]) {
	    val puppy = new Puppy
	    val dog = new Dog

	    val puppyAnimal:Animal[Puppy] = new Animal[Puppy](puppy)
	    val dogAnimal:Animal[Dog] = new Animal[Dog](dog)

	    val dogCarer = new AnimalCarer(dogAnimal)
	    val puppyCarer = new AnimalCarer(puppyAnimal)

	    println("Done.")
	  }
	}


If we remove Variance Annotation in Animal class definition, like as shown below:

	class Animal[T](val animial:T)
	// Remaining code is same as above.

	Type mismatch, expected: Animal[Dog], found: Animal[Puppy]

----------------------------------------------------------------------------------------------------------------------
Contravariant in Scala
	
	If “S” is subtype of “T” then List[T] is is a subtype of List[S].

	This kind of Inheritance Relationship between two Parameterized Types is known as “Contravariant”

	T <- S
	List[S] <- List[T]

	To represent Contravariant relationship between two Parameterized Types, Scala uses the following syntax:

	Ex : List[-T]

	Prefixing Type Parameter with “-” symbol defines Contravariant in Scala.


	abstract class Type [-T]{
	  def typeName : Unit
	}

	class SuperType extends Type[AnyVal]{
	  override def typeName: Unit = {
	    println("SuperType")
	  }
	}
	class SubType extends Type[Int]{
	  override def typeName: Unit = {
	    println("SubType")
	  }
	}

	class TypeCarer{
	  def display(t: Type[Int]){
	    t.typeName
	  }
	}

	object ScalaContravarianceTest {

	  def main(args: Array[String]) {
	    val superType = new SuperType
	    val subType = new SubType

	    val typeCarer = new TypeCarer

	    typeCarer.display(subType)
	    typeCarer.display(superType)
	  }

	}


-----------------------------------------------------------------------------------------


Invariant in Scala

	If “S” is subtype of “T” then List[S] and List[T] don’t have Inheritance Relationship or Sub-Typing. That means both are unrelated.

	This kind of Relationship between two Parameterized Types is known as “Invariant or Non-Variant”

	In Scala, by default Generic Types have Non-Variant relationship. If we define Parameterized Types without using “+’ or “-” symbols, then they are known as Invariants.


Notes

	Q[A <: B] means that class Q can take any class A that is a subclass of B.

	Q[+B] means that Q can take any class, but if A is a subclass of B, then Q[A] is considered to be a subclass of Q[B].

	Q[+A <: B] means that class Q can only take subclasses of B as well as propagating the subclass relationship.

	The first is useful when you want to do something generic, but you need to rely upon a certain set of methods in B. For example, if you have an Output class with a toFile method, you could use that method in any class that could be passed into Q.

	The second is useful when you want to make collections that behave the same way as the original classes. If you take B and you make a subclass A, then you can pass A in anywhere where B is expected. But if you take a collection of B, Q[B], is it true that you can always pass in Q[A] instead? In general, no; there are cases when this would be the wrong thing to do. But you can say that this is the right thing to do by using +B (covariance; Q covaries--follows along with--B's subclasses' inheritance relationship).

