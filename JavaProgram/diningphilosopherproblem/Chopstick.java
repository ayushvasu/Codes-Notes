package com.diningphilosopherproblem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	
	private int id;
	private Lock lock;
	
	public Chopstick(int id) {
		this.id = id;
		lock = new ReentrantLock(true);
	}
	
	public boolean pickUp(Philosopher phs,State state) throws InterruptedException {
		if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {
			System.out.println(phs +" picks up "+state.toString()+"  "+this);
			return true;
		}
		return false;
	}
	
	public void putDown(Philosopher phs,State state) throws InterruptedException {
		lock.unlock();
		System.out.println(phs+" puts down "+state.toString()+" "+this);
	}
	
	public String toString() {
		return " Chopstick : "+id;
	}

}
