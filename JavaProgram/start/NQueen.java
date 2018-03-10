package com.start;

import java.util.Scanner;

public class NQueen {
	static int N = 0;

	public static void main(String[] ar) {
		Scanner s = new Scanner(System.in);
		System.out.println("Value of N:::");
		N = s.nextInt();
		int[][] board = new int[N][N];
		if (boardSolver(board, 0)) {
			System.out.println("Solution Found");
			printBoard(board);
		} else {
			System.out.println("No Solution Found");
		}
		s.close();
	}

	static void printBoard(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1)
					System.out.print("Q\t");
				else
					System.out.print("_\t");
			}
			System.out.println("");
		}
	}

	static boolean boardSolver(int[][] board, int col) {

		if (col >= N)
			return true;
		for (int i = 0; i < N; i++) {

			if (toPlace(board, i, col)) {
				System.out.println("for i" + i + "   col:" + col);
				printBoard(board);
				board[i][col] = 1;
				if (boardSolver(board, col + 1))
					return true;
				else
					board[i][col] = 0;
			}

		}
		return false;
	}

	static boolean toPlace(int[][] board, int row, int col) {
		int i, j;
		for (i = col; i >= 0; i--) {
			if (board[row][i] == 1) {
				return false;
			}
		}
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		for (i = row, j = col; i < N && j >= 0; i++, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		return true;
	}
}
