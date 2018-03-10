package com.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Process{
	INSTANCE;
	
	private Semaphore smp = new Semaphore(3, true);
	
	public void downloadData() {
		try {
			smp.acquire();
			download();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			smp.release();
		}
	}

	private void download() {
		System.out.println("Download Data with Thread :: "+Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class Main {
	public static void main(String[] ar) {
		ExecutorService exs = Executors.newCachedThreadPool();
		for(int i=0;i<12;i++) {
			exs.submit(new Runnable() {
				
				@Override
				public void run() {
					Process.INSTANCE.downloadData();
					
				}
			});
		}
		
		exs.shutdown();
		
	}

}
