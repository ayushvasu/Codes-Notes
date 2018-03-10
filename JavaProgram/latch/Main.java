package com.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] ar) {
		ExecutorService exs = Executors.newSingleThreadExecutor();
		
		CountDownLatch cdl = new CountDownLatch(5);
		
		for(int i=0;i<5;i++) {
			exs.execute(new Worker(i+1, cdl));
		}
		
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Latch Count become zero");
		exs.shutdown();
	}

}
