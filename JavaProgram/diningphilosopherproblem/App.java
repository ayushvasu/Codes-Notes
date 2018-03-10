package com.diningphilosopherproblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
	
	public static void main(String[] ar) throws InterruptedException {
		ExecutorService exs = null;
		Philosopher[] phs = null;
		try {
			Chopstick ch[] = new Chopstick[Constants.NUM_OF_CHOPSTICK];
			for(int i=0;i<Constants.NUM_OF_CHOPSTICK;i++) {
				ch[i] = new Chopstick(i);
			}
			phs = new Philosopher[Constants.NUM_OF_PHILOSOPHER];
			exs = Executors.newFixedThreadPool(Constants.NUM_OF_PHILOSOPHER);
			
			for(int i=0;i<Constants.NUM_OF_PHILOSOPHER;i++) {
				phs[i] = new Philosopher(i, ch[i], ch[(i+1) % Constants.NUM_OF_CHOPSTICK]);
				exs.execute(phs[i]);
			}
			Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
			System.out.println("Setting full");
			for(Philosopher ph:phs)
				ph.setFull(true);
			
		} finally {
			exs.shutdown();
			while(!exs.isTerminated())
				Thread.sleep(100);
			for(Philosopher ph : phs) {
				System.out.println(ph.toString()+" eats for "+ph.getEating_counter());
			}
		}
		
	}

}
