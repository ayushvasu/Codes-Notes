package com.mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMerge extends RecursiveAction {
	int[] num;
	Sequential sm;

	public ParallelMerge(int[] num, Sequential sm) {
		this.num = num;
		this.sm = sm;
	}

	@Override
	protected void compute() {
		if (num.length <= 10) {
			sm.mergeSort(num);
			return;
		}
		int middleIndex = num.length / 2;
		int[] left = Arrays.copyOfRange(num, 0, middleIndex);
		int[] right = Arrays.copyOfRange(num, middleIndex, num.length);
		
		ParallelMerge p1 = new ParallelMerge(left, sm);
		ParallelMerge p2 = new ParallelMerge(right, sm);
		
		invokeAll(p1,p2);
		sm.merge(left, right, num);
	}

}
