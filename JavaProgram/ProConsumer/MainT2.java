package com.ProConsumer;

public class MainT2 {
	
	
	public static void main(String[] ar) {
		ProcessorT2 pro = new ProcessorT2();

		
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pro.producer();
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
					pro.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
