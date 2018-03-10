package com.start;
import java.util.Arrays;

public class NumWaysInMatrix {
	static int N = 5, M = 6;

	public static void main(String ar[]) {
		int[][] mat = new int[N][M];
		for (int i = 0; i < N; i++)
			Arrays.fill(mat[i], 1);
		for (int i = 1; i < N; i++) {
			printMatrix(mat);
			for (int j = 1; j < M; j++) {
				mat[i][j] = mat[i - 1][j] + mat[i][j - 1];
			}
		}
		printMatrix(mat);
	}

	static void printMatrix(int[][] a) {
		// System.out.println(""+a[0].length);
		// System.out.println(""+a.length);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("------------------------------------------------");
	}
}
