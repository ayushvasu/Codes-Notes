import scala.concurrent.{ExecutionContext, Future}

def getX:Future[Option[Int]] = Future(3)
def getY:Future[Option[Int]] = Future(5)


val z: Future[Option[Int]] = for {
	x <- getX
	y <- getY
} yield x + y

def getX:Future[Option[Int]] = Future(Some(3))
def getY:Future[Option[Int]] = Future(Some(5))

case class FutureOption[A](inner: Future[Option[A]]) {
	
	def map[B](f: A => B): FutureOption[B] = FutureOption {
		inner.map { _.map{ f } }
	}

	def flatMap[B](f: A => FutureOption[B]): FutureOption[B] = {
		FutureOption {
			inner.flatMap {
				case Some(a) => f(a).inner
				case None => Future.successful(None)
			}
		}
	}
}

val z = (for {
	x <- FutureOption(getX)
	y <- FutureOption(getY)
} yield x + y).inner

FutureOption(getX).flatMap{
	x => FutureOption(getY).map{
		y => x + y
	}
}


trait Monad[M[_]] {
	def map[A,B](ma: M[A], f: A => B): M[B]
	def flatMap[A,B](ma: M[A], f: A => M[B]): M[B]
	def create[A](a: A): M[A]
}
