package com.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor implements Callable<String>{
	String str;
	
	public Processor(String str) {
		this.str = str;
	}

	@Override
	public String call() throws Exception {
		System.out.println("Thread Task iniciate ::"+Thread.currentThread().getName());
		Thread.sleep(1000);
		return "ID ::"+str;
	}
	
}

public class Main {
	
	public static void main(String[] ar) {
		ExecutorService exs = Executors.newFixedThreadPool(3);
		
		List<Future<String>> flst = new ArrayList<>();
		
		for(int i=0;i<10;i++) {
			Future<String> future = exs.submit(new Processor(""+i));
			flst.add(future);
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		for(Future<String> f:flst) {
			try {
				System.out.println(f.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		exs.shutdown();
	}

}
