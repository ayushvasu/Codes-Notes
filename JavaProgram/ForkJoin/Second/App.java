package com.Second;

import java.util.concurrent.ForkJoinPool;

public class App {
	
	public static void main(String[] ar) {
		ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecurTask s = new SimpleRecurTask(120);
		System.out.println(fjp.invoke(s));
	}

}
