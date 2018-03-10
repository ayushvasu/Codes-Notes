package com.exchanger;

import java.util.concurrent.Exchanger;

class Worker1 implements Runnable {
	private int count;

	Exchanger<Integer> exc;

	public Worker1(Exchanger<Integer> exc) {
		this.exc = exc;
	}

	@Override
	public void run() {
		for (int i = 0; i < 15; i++) {
			count = count + 1;
			System.out.println("+1 :" + count);
			try {
				count = exc.exchange(count);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class Worker2 implements Runnable {
	private int count;

	Exchanger<Integer> exc;

	public Worker2(Exchanger<Integer> exc) {
		this.exc = exc;
	}

	@Override
	public void run() {
		for (int i = 0; i < 15; i++) {
			count = count - 1;
			System.out.println("-1 :" + count);
			try {
				count = exc.exchange(count);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class Main {
	public static void main(String ar[]) {
		Exchanger<Integer> exc = new Exchanger<>();
		Thread t1 = new Thread(new Worker1(exc));
		Thread t2 = new Thread(new Worker2(exc));
		t1.start();
		t2.start();

	}
}
