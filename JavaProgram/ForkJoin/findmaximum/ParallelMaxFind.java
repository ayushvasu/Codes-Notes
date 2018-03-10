package com.findmaximum;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFind extends RecursiveTask<Integer> {

	int[] num;
	int low, high;

	public ParallelMaxFind(int[] num, int low, int high) {
		this.num = num;
		this.low = low;
		this.high = high;
	}

	@Override
	protected Integer compute() {
		if (high - low > App.THRESHOLD) {
			return sqequentialMaxFind(num, high, low);
		} else {
			int middle = (low + high) / 2;
			ParallelMaxFind p1 = new ParallelMaxFind(num, low, middle);
			ParallelMaxFind p2 = new ParallelMaxFind(num, middle, high);
			invokeAll(p1, p2);
			return Math.max(p1.join(), p2.join());
		}
	}

	public int sqequentialMaxFind(int[] num, int highIndex, int low) {

		int max = num[low];
		for (int i = low + 1; i < highIndex; i++)
			if (max < num[i])
				max = num[i];

		return max;
	}

}
