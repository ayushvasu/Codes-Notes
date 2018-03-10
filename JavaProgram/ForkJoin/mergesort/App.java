package com.mergesort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
	public static void main(String[] ar) {
		int[] arr = initArray();
		Sequential sm = new Sequential();
		ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		ParallelMerge p = new ParallelMerge(arr, sm);
		//ForkJoinPool.commonPool().invoke(p);
		fjp.invoke(p);
		System.out.println("Done");
		//System.out.println(Arrays.toString(arr));
	}

	private static int[] initArray() {
		Random ran = new Random();
		int[] num = new int[10000000];
		for (int i = 0; i < 10000000; i++)
			num[i] = ran.nextInt(1000000);
		return num;
	}
}
