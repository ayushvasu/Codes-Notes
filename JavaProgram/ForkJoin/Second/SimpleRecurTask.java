package com.Second;

import java.util.concurrent.RecursiveTask;

public class SimpleRecurTask extends RecursiveTask<Integer> {
	private int simulatedWork;
	
	public SimpleRecurTask(int simulatedWork) {
		this.simulatedWork = simulatedWork;
	}

	@Override
	protected Integer compute() {
		if(simulatedWork > 100) {
			System.out.println("Task is bigg dividing"+simulatedWork);
			SimpleRecurTask s1 = new SimpleRecurTask(simulatedWork/2);
			SimpleRecurTask s2 = new SimpleRecurTask(simulatedWork/2);
			s1.fork();
			s2.fork();
			int solution = 0 ;
			solution += s1.join();
			solution += s2.join();
			return solution;
		}else {
			System.out.println("No need to split "+simulatedWork);
			return simulatedWork*2;
		}
	}

	
}
