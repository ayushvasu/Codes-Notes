package com.concurrentMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Main {
public static void main(String[] ar) {
	ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
	Thread t1 = new Thread(new Worker1(map));
	Thread t2 = new Thread(new Worker2(map));
	t1.start();
	t2.start();
}
}

class Worker1 implements Runnable{
	ConcurrentMap<String, Integer> map;
	
	public Worker1(ConcurrentMap<String,Integer> map) {
		this.map = map;
	}
			

	@Override
	public void run() {
		map.putIfAbsent("E", 12);
		map.putIfAbsent("r", 126);
		map.putIfAbsent("w", 112);
		map.putIfAbsent("g", 1332);
	}
	
}
class Worker2 implements Runnable{
	ConcurrentMap<String, Integer> map;
	
	public Worker2(ConcurrentMap<String,Integer> map) {
		this.map = map;
	}
			

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map.get("E"));
		System.out.println(map.get("r"));
		System.out.println(map.get("w"));
		System.out.println(map.get("g"));
	}
	
}
