package com.ProConsumer;

import java.util.List;
import java.util.ArrayList;

public class Processor {
	
	private List<Integer> lst = new ArrayList<>();
	private final int Bottom = 0;
	private final int Limit = 10;
	private final Object lock = new Object();
	private int value = 0;
	

	public void producer() throws InterruptedException {
		synchronized (lock) {
			while(true) {
				if(lst.size() == Limit) {
					System.out.println("Buffer is full!!");
					lock.wait();
				}else {
					System.out.println("Adding Value to Buffer!! "+value);
					lst.add(value);
					value++;
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}

	public void consumer() throws InterruptedException {
		synchronized (lock) {
			while(true) {
				if(lst.size() <= Bottom) {
					System.out.println("Waitting to add Vlue");
					lock.wait();
				}else {
					System.out.println("Get and remove Value :"+lst.remove(--value));
					lock.notify();
				}
				Thread.sleep(500);
			}
			
		}
	}
	
}
