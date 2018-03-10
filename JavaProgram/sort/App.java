package com.sort;

import java.util.Random;

public class App {

	public static void main(String[] ar) {
		int numOfThread = Runtime.getRuntime().availableProcessors();
		System.out.println("Number of thread " + numOfThread);
		int[] numbers = createRandomArray();
		MergeSortNew mergesort = new MergeSortNew(numbers);

		long start = System.currentTimeMillis();
		mergesort.parallelMergeSort(0, numbers.length - 1, numOfThread);
		long end = System.currentTimeMillis();
		System.out.println("time taken "+(end-start));
		
		start = System.currentTimeMillis();
		mergesort.mergeSort(0, numbers.length - 1);
		end = System.currentTimeMillis();
		System.out.println("time taken "+(end-start));
		
	}

	private static int[] createRandomArray() {
		int[] a = new int[50000000];
		Random random = new Random();
		for (int i = 0; i < 50000000; i++)
			a[i] = random.nextInt(100000);
		return a;
	}

}
