package com.delayqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] ar) {

		BlockingQueue<DelayWorker> queue = new DelayQueue<>();
		try {
			queue.put(new DelayWorker(1000, "Message 1!!"));
			queue.put(new DelayWorker(10000, "Message 2!!"));
			queue.put(new DelayWorker(3000, "Message 3!!"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (!queue.isEmpty()) {
			try {
				System.out.println(queue.take());
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

}

class DelayWorker implements Delayed {
	private long duration;
	private String message;
	
	public DelayWorker(long duration,String message) {
		this.duration = System.currentTimeMillis() + duration;
		this.message = message;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int compareTo(Delayed that) {
		if(this.getDuration() <((DelayWorker)that).getDuration())
			return -1;
		if(this.getDuration() >((DelayWorker)that).getDuration())
			return 1;		
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MICROSECONDS);
	}
	
	public String toString() {
		return message;
	}

}
