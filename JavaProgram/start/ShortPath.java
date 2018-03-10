package com.start;
import java.util.LinkedList;
import java.util.Queue;

public class ShortPath {
	int ROW = 9;
	int COL = 10;
	int rowNum[] = { -1, 0, 0, 1 };
	int colNum[] = { 0, -1, 1, 0 };

	public static void main(String ar[]) {
		int[][] adj = { { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
				{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
				{ 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 } };
		Point source = new Point(0, 0);
		Point end = new Point(3, 4);
		//System.out.println("Calling BFS");
		int distance_path = new ShortPath().BFS(adj, source, end);
		System.out.println("Distance Path : "+distance_path);

	}// main

	boolean isValid(int row, int col) {
		return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
	}

	int BFS(int[][] adj, Point src, Point end) {
		//System.out.println("In BFS");
		boolean[][] visit = new boolean[ROW][COL];
		visit[src.x][src.y] = true;
		Queue<Cell> q = new LinkedList<Cell>();
		q.add(new Cell(src, 0));

		while (!q.isEmpty()) {
			Cell ele = q.remove();
			Point ple = ele.p;
			System.out.println("At Point :"+ple.x+" , "+ple.y);
			
			if (ple.x == end.x && ple.y == end.y) {
				return ele.dist;
			}
			for (int i = 0; i < 4; i++) {
				int row = ple.x + rowNum[i];
				int col = ple.y + colNum[i];
				if (isValid(row, col) && adj[row][col] != 0 && !visit[row][col]) {
					visit[row][col] = true;
					q.add(new Cell(new Point(row, col), ele.dist + 1));
				}
			}
			//printMatrix(visit);
		}
		return Integer.MAX_VALUE;
	}// bfs
	
	
	static void printMatrix(boolean[][] a) {
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

}// class

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Cell {
	Point p;
	int dist;

	Cell(Point p, int dist) {
		this.p = p;
		this.dist = dist;
	}
}
