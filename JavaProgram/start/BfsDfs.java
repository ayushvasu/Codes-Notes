package com.start;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BfsDfs {

	public static void main(String[] ar) {
		int[][] adj = { { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 1, 0, 0, 1 },
				{ 0, 0, 0, 1 } };
		bFS(adj);
		dFS(adj);
	}
	public static void bFS(int[][] adj){
		System.out.println("BFS");
		int[] visit = new int[adj[0].length];
		Arrays.fill(visit, 0);
		Queue<Integer> q = new LinkedList<Integer>();
		int source = 0;
		visit[source] = 1;
		q.add(source);
		while(!q.isEmpty()){
			int element = q.remove();
			int i = element;
			System.out.print(element +"  ");
			while(i<adj[0].length){
				if(adj[element][i] == 1 && visit[i] == 0){
					visit[i]=1;
					q.add(i);
				}
				i++;
			}
		}
	}
	
	
	public static void dFS(int[][] adj){
		System.out.println("\nDFS");
		int[] visit = new int[adj[0].length];
		Arrays.fill(visit, 0);
		Stack<Integer> stk = new Stack<Integer>();
		int source = 0;
		visit[source] = 1;
		stk.push(source);
		while(!stk.isEmpty()){
			int element = stk.pop();
			int i = element;
			System.out.print(element +"  ");
			while(i<adj[0].length){
				if(adj[element][i] == 1 && visit[i] == 0){
					visit[i]=1;
					stk.push(i);
				}
				i++;
			}
		}
	}
}
