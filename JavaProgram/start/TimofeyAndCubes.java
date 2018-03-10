package com.start;
import java.util.Scanner;

public class TimofeyAndCubes {

	public static void main(String ar[]) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
		}
		int i = 0;
		int b;
		while (i < n - i - 1) {
            b = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = b;
            i += 2;
        }
		StringBuilder ss = new StringBuilder();
		for (int ai : arr) {
            ss.append(ai).append(" ");
        }
        System.out.println(ss);
        s.close();
	}
}
