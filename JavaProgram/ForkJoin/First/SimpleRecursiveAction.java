package com.First;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {
	private int simulatedWork;

	public SimpleRecursiveAction(int simulatedWork) {
		this.simulatedWork = simulatedWork;
	}

	@Override
	protected void compute() {
		if (simulatedWork > 100) {
			System.out.println("Big task splitting " + simulatedWork);
			SimpleRecursiveAction s1 = new SimpleRecursiveAction(simulatedWork / 2);
			SimpleRecursiveAction s2 = new SimpleRecursiveAction(simulatedWork / 2);
			s1.fork();
			s2.fork();
		} else {
			System.out.println("task is small enough "+simulatedWork);
		}
	}

}
