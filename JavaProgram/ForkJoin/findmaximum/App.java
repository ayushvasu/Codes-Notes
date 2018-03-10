package com.findmaximum;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
	public static int THRESHOLD = 0;

	public static void main(String[] ar) {
		int[] num = initArray();
		THRESHOLD = num.length / Runtime.getRuntime().availableProcessors();
		SqequentialMaxFind s1 = new SqequentialMaxFind();
		long start = System.currentTimeMillis();
		int max = s1.sqequentialMaxFind(num, num.length);
		System.out.println(max +" Sequential time "+(System.currentTimeMillis()-start));
		
		start = System.currentTimeMillis();
		ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		ParallelMaxFind p1 = new ParallelMaxFind(num, 0, num.length);
		System.out.println(fjp.invoke(p1) +" Parallel time "+(System.currentTimeMillis()-start));
	}

	private static int[] initArray() {
		Random ran = new Random();
		int[] num = new int[100000000];
		for (int i = 0; i < 100000000; i++)
			num[i] = ran.nextInt(100000);

		return num;
	}

}
