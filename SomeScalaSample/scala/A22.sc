object A22 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def fact(x: Int): Int = if (x <= 1) 1 else x * fact(x - 1)
                                                  //> fact: (x: Int)Int
  fact(3)                                         //> res0: Int = 6
  def fact_2(x: Int): Int = {
    def loop(acc: Int, x: Int): Int = if (x == 1) acc else loop(acc * x, x - 1)
    loop(1, x)
  }                                               //> fact_2: (x: Int)Int
  fact_2(6)                                       //> res1: Int = 720

  def sum(a: Int, b: Int, f: Int => Int): Int = if (a > b) 0 else f(a) + sum(a + 1, b, f)
                                                  //> sum: (a: Int, b: Int, f: Int => Int)Int

  sum(2, 4, fact)                                 //> res2: Int = 32
	
	def sum_fun(f:Int=>Int):(Int,Int)=>Int={
	def loop(a:Int,b:Int):Int=if(a>b) 0 else f(a)+loop(a+1,b)
	loop
	}                                         //> sum_fun: (f: Int => Int)(Int, Int) => Int
	def sum_fact=sum_fun(fact)                //> sum_fact: => (Int, Int) => Int
	sum_fact(1,3)                             //> res3: Int = 9
	def sum_pow2 = sum_fun(x=>x*x)            //> sum_pow2: => (Int, Int) => Int
	sum_pow2(1,5)                             //> res4: Int = 55
	
	
	def sum_fun2(f:Int=>Int)(a:Int,b:Int):Int={
	def loop(a:Int,acc:Int):Int={
	if(a>b) acc else loop(a+1,acc*a)}
	loop(0,a)
	}                                         //> sum_fun2: (f: Int => Int)(a: Int, b: Int)Int
	sum_fun(fact)(2,4)                        //> res5: Int = 32
	
	def sum_fun3(f:Int=>Int,Combine:(Int,Int)=>Int,unit:Int)(a:Int,b:Int):Int={
	if(a>b) unit else Combine(f(a),sum_fun3(f,Combine,unit)(a+1,b))
	}                                         //> sum_fun3: (f: Int => Int, Combine: (Int, Int) => Int, unit: Int)(a: Int, b: 
                                                  //| Int)Int
	def sum_fact_fun(n:Int)=sum_fun3(fact,(x,y)=>x*y,1)(1,n)
                                                  //> sum_fact_fun: (n: Int)Int
	sum_fact_fun(5)                           //> res6: Int = 34560
	
	def sum_fact_fun_2(f:Int=>Int)(a:Int,b:Int):Int = sum_fun3(f,(x,y)=>x+y,0)(a,b)
                                                  //> sum_fact_fun_2: (f: Int => Int)(a: Int, b: Int)Int
	sum_fact_fun_2(fact)(2,7)                 //> res7: Int = 5912
	
	}