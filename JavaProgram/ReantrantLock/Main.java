package com.ReantrantLock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;


public class Main {
	
	private static Lock lck = new ReentrantLock();
	static int counter = 0;
	
	static void task() {
		try {
			while(true) {
				if(lck.tryLock()) {
					for(int i = 0;i<100000;i++) counter++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}else {
					System.out.println(Thread.currentThread().getName()+"  Waitng Lock is already accuire");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			lck.unlock();
		}
	}
	
	public static void main(String ar[]) {
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				task();
			}
		});
		th1.setName("@1");
		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				task();
			}
		});
		th2.setName("@2");
		
		th1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th2.start();
		
		try {
			th2.join();
			th1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(counter);
		
	}

}
