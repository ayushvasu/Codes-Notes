package com.diningphilosopherproblem;

import java.util.Random;

public class Philosopher implements Runnable {
	private int id;
	private Chopstick right;
	private Chopstick left;
	private Random random;
	private int eating_counter;
	private volatile boolean isFull = false;

	public Philosopher(int id, Chopstick right, Chopstick left) {
		this.id = id;
		this.right = right;
		this.left = left;
		random = new Random();
	}

	@Override
	public void run() {

		try {
			while (!isFull) {
				think();
				if (left.pickUp(this, State.LEFT)) {
					if (right.pickUp(this, State.RIGHT)) {
						eat();
						right.putDown(this, State.RIGHT);
					}
					left.putDown(this, State.LEFT);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public int getEating_counter() {
		return eating_counter;
	}

	public void setEating_counter(int eating_counter) {
		this.eating_counter = eating_counter;
	}
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	private void think() throws InterruptedException {
		System.out.println(this + " is thinking....");
		Thread.sleep(random.nextInt(1000));
	}

	private void eat() throws InterruptedException {
		System.out.println(this + " is eating....");
		Thread.sleep(random.nextInt(1000));
		eating_counter += 1;
	}

	public String toString() {
		return "philosopher :" + id;
	}
}
