//sequential program

import scala.util.Try
// Some type aliases, just for getting more meaningful method signatures:
type CoffeeBeans = String
type GroundCoffee = String
case class Water(temperature: Int)
type Milk = String
type FrothedMilk = String
type Espresso = String
type Cappuccino = String
// dummy implementations of the individual steps:
def grind(beans: CoffeeBeans): GroundCoffee = s"ground coffee of $beans"
def heatWater(water: Water): Water = water.copy(temperature = 85)
def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"
def brew(coffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"
def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"
// some exceptions for things that might go wrong in the individual steps
// (we'll need some of them later, use the others when experimenting
// with the code):
case class GrindingException(msg: String) extends Exception(msg)
case class FrothingException(msg: String) extends Exception(msg)
case class WaterBoilingException(msg: String) extends Exception(msg)
case class BrewingException(msg: String) extends Exception(msg)
// going through these steps sequentially:
def prepareCappuccino(): Try[Cappuccino] = for {
  ground <- Try(grind("arabica beans"))
  water <- Try(heatWater(Water(25)))
  espresso <- Try(brew(ground, water))
  foam <- Try(frothMilk("milk"))
} yield combine(espresso, foam)


//Future implementation
import scala.concurrent.future
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Random

def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
  println("start grinding...")
  Thread.sleep(Random.nextInt(2000))
  if (beans == "baked beans") throw GrindingException("are you joking?")
  println("finished grinding...")
  s"ground coffee of $beans"
}

def heatWater(water: Water): Future[Water] = Future {
  println("heating the water now")
  Thread.sleep(Random.nextInt(2000))
  println("hot, it's hot!")
  water.copy(temperature = 85)
}

def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
  println("milk frothing system engaged!")
  Thread.sleep(Random.nextInt(2000))
  println("shutting down milk frothing system")
  s"frothed $milk"
}

def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
  println("happy brewing :)")
  Thread.sleep(Random.nextInt(2000))
  println("it's brewed!")
  "espresso"
}


//Future
object Future {
  def apply[T](body: => T)(implicit execctx: ExecutionContext): Future[T]
}

//call back
import scala.util.{Success, Failure}
grind("baked beans").onComplete {
  case Success(ground) => println(s"got my $ground")
  case Failure(ex) => println("This grinder needs a replacement, seriously!")
}

val temperatureOkay: Future[Boolean] = heatWater(Water(25)).map { water =>
  println("we're in the future!")
  (80 to 85).contains(water.temperature)
}

def prepareCappuccino(): Future[Cappuccino] = {
  val groundCoffee = grind("arabica beans")
  val heatedWater = heatWater(Water(20))
  val frothedMilk = frothMilk("milk")
  for {
    ground <- groundCoffee
    water <- heatedWater
    foam <- frothedMilk
    espresso <- brew(ground, water)
  } yield combine(espresso, foam)
}


//You can create an ExecutionContext
import java.util.concurrent.Executors
import concurrent.ExecutionContext
val executorService = Executors.newFixedThreadPool(4)
val executionContext = ExecutionContext.fromExecutorService(executorService)

//Wait for all future to complete before stop vm
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

val f1 = Future(Thread.sleep(1000))
val f2 = Future(Thread.sleep(2000))
val all = Future.sequence(Seq(f1, f2))
all.foreach(_ => println("All done"))

f1.foreach(_ => println("Future 1 done"))
f2.foreach(_ => println("Future 2 done"))

// Wait for the futures to complete before shutting down
Await.result(all, 5.seconds)
