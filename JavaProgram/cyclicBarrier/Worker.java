package com.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {
	private int id;
	private Random random;
	private CyclicBarrier cb;

	public Worker(int id, CyclicBarrier cb) {
		this.id = id;
		this.cb = cb;
		random = new Random();
	}

	@Override
	public void run() {
		doWork();
	}

	private void doWork() {
		System.out.println("Thread with id :: " + id + " starts the task..");
		try {
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread with id :: " + id + " finished the task..");

		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

	public String toString() {
		return "thrad id :: "+id;
	}
}
