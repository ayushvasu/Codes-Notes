package com.listTest

object Pairs {
  val n = 7                                       //> n  : Int = 7
 (1 until n) flatMap (i=>
 	(1 until i) map (j => (i,j))) filter (pair =>
 		pair._1 + pair._2 > 9)            //> res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((6,4), (6,5
                                                  //| ))
 		
 	(for{
 	i <- 1 until n
 	j <- 1 until i
 	if(i+j > 9)
 	} yield (i,j)) toList                     //> res1: List[(Int, Int)] = List((6,4), (6,5))
}