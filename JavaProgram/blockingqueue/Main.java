package com.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker1 implements Runnable{
	private BlockingQueue<Integer> bq ;
	
	public Worker1(BlockingQueue<Integer> bq) {
		this.bq = bq;
	}

	@Override
	public void run() {
		int count = 0;
		while(true) {
			try {
				bq.put(count);
				System.out.println("Putting !!"+count);
				Thread.sleep(500);
				count++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}	
}

class Worker2 implements Runnable{
	private BlockingQueue<Integer> bq ;
	
	public Worker2(BlockingQueue<Integer> bq) {
		this.bq = bq;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				int num = bq.take();
				System.out.println("Taking Item!!!  "+num);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}	
}



public class Main {
	
	public static void main(String[] ar) {
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(5);
		Worker1 w1 = new Worker1(bq);
		Worker2 w2 = new Worker2(bq);
		ExecutorService exs = Executors.newFixedThreadPool(3);
		exs.submit(w1);
		exs.submit(w2);
		exs.shutdown();
		
	}

}
