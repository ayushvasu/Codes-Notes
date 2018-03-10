package com.sesssion1;

public class WaitTask {

	public void producer() throws InterruptedException {
		synchronized (this) {
			System.out.println("Produced now wait!!");
			wait();
			System.out.println("Done Waiting Consumer Consumed!!");
		}
	}

	public void consumer() throws InterruptedException {
		Thread.sleep(300);
		synchronized (this) {
			System.out.println("Consumed now Notify!!!");
			notify();
		}
	}

	public static void main(String[] ar) {
		WaitTask ob = new WaitTask();
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ob.producer();
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
					ob.consumer();
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
