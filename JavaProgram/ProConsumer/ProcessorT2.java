package com.ProConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessorT2 {
	private Lock lck = new ReentrantLock();
	private Condition cnd = lck.newCondition();
	
	public void producer() throws InterruptedException {
		lck.lock();
		System.out.println("Producer!!");
		cnd.await();
		System.out.println("Again Producer!!");
		lck.unlock();
	}

	public void consumer() throws InterruptedException {
		Thread.sleep(10);
		lck.lock();
		System.out.println("Consumer!!");
		cnd.signal();
		lck.unlock();
	}
}
