package com.ProConsumer;

public class Main {

	public static void main(String ar[]) {
		Processor proc = new Processor();
		
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					proc.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					proc.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		th1.start();
		th2.start();
		
		try {
			th2.join();
			th1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
