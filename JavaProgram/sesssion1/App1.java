package com.sesssion1;

class Runner1 extends Thread {
	public void run() {
		for (int i = 0; i < 10000; i++) {
			System.out.println("Runner1 :" + i);
			App1.increment();
		}
	}
}

class Runner2 implements Runnable {
	public void run() {
		for (int i = 0; i < 10000; i++) {
			System.out.println("Runner2 :" + i);
			App1.increment();
		}
	}
}

public class App1 {
	static int count = 0;
	public static void main(String[] args) {
		Runner1 tr1 = new Runner1();
		Runner2 r2 = new Runner2();
		Thread tr2 = new Thread(r2);

		tr1.start();
		tr2.start();

		try {
			tr1.join();
			tr2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.print("Tasks Finish with:" + count);
	}
	
	static synchronized void increment() {
		count ++;
	}

}
