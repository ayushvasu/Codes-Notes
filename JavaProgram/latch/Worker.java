package com.latch;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable{
	private int id;
	private CountDownLatch cdl;
	
	public Worker(int id,CountDownLatch cdl) {
		this.id = id;
		this.cdl = cdl;
	}

	@Override
	public void run() {
		doWork();
		cdl.countDown();
	}

	private void doWork() {
		System.out.println("Thread with id::"+id+" starts its tasks...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
