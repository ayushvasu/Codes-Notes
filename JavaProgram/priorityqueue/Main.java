package com.priorityqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

	public static void main(String[] ar) {
		BlockingQueue<String> queue = new PriorityBlockingQueue<>();
		Thread t1 = new Thread(new Worker1(queue));
		Thread t2 = new Thread(new Worker2(queue));
		t1.start();
		t2.start();
	}

}

class Worker1 implements Runnable {
	BlockingQueue<String> queue;

	public Worker1(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			queue.put("E");
			queue.put("B");
			Thread.sleep(1000);
			queue.put("C");
			queue.put("A");
			Thread.sleep(1000);
			queue.put("D");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Worker2 implements Runnable {
	BlockingQueue<String> queue;

	public Worker2(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			System.out.println(queue.take());
			Thread.sleep(5000);
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
