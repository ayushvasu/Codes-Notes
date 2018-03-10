package com.First;

import java.util.concurrent.ForkJoinPool;

public class App {
	
	public static void main(String ar[]) {
		ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction s = new SimpleRecursiveAction(120);
		fjp.invoke(s);
	}

}
