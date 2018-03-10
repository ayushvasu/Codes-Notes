package com.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] ar) {
		ExecutorService exs = Executors.newFixedThreadPool(5);
		CyclicBarrier cb = new CyclicBarrier(5,new Runnable() {
			@Override
			public void run() {
				System.out.println("Tasks Finished !!");
			}
		});
		for(int i=0;i<5;i++)
			exs.submit(new Worker(i+1,cb));
		System.out.println("Exs shutdown!!");
		exs.shutdown();
		
	}
}
