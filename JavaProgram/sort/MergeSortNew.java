package com.sort;

import java.util.Arrays;

public class MergeSortNew {
	private int[] nums;
	private int[] tempArray;

	MergeSortNew(int[] nums) {
		this.nums = nums;
		tempArray = new int[nums.length];

	}

	public void parallelMergeSort(int low, int high, int numofThread) {
		if (numofThread <= 1) {
			mergeSort(low, high);
			return;
		}
		int middle = (low + high) / 2;
		Thread t1 = mergeSortParallel(low, middle, numofThread);
		Thread t2 = mergeSortParallel(middle+1, high, numofThread);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		merge(low,middle,high);

	}

	private Thread mergeSortParallel(int low, int high, int numofThread) {
		return new Thread() {
			public void run() {
				parallelMergeSort(low, high, numofThread / 2);
			}
		};
	}

	public void mergeSort(int low, int high) {
		if (low >= high)
			return;
		int middle = (low + high) / 2;
		mergeSort(low, middle);
		mergeSort(middle + 1, high);
		merge(low, middle, high);
	}

	private void merge(int low, int middle, int high) {
		for (int i = low; i <= high; i++)
			tempArray[i] = nums[i];
		int i = low;
		int j = middle + 1;
		int k = low;
		while ((i <= middle) && (j <= high)) {
			if (tempArray[i] <= tempArray[j]) {
				nums[k] = tempArray[i];
				i++;
			} else {
				nums[k] = tempArray[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			nums[k] = tempArray[i];
			k++;
			i++;
		}
		while (j <= high) {
			nums[k] = tempArray[j];
			k++;
			j++;
		}

	}

	public boolean isSorted() {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > nums[i + 1])
				return false;
		}
		return true;
	}

	public void showResult() {
		System.out.println(Arrays.toString(this.nums));
	}

}
