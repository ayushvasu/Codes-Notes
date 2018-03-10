package com.start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Start1 {

	public static void main(String ar[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		// String ss = sc.nextLine().replaceAll(" ", "");
		// char[] c = ss.toCharArray();
		char[] c = new char[n];
		for (int i = 0; i < n; i++)
			c[i] = sc.next().charAt(0);
		HashMap<Integer, LinkedList<Integer>> charMap = new HashMap<>();
		for (int j = 1; j < n; j++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			if (!charMap.containsKey(u))
				charMap.put(u, new LinkedList<Integer>());
			charMap.get(u).add(v);

			if (!charMap.containsKey(v))
				charMap.put(v, new LinkedList<Integer>());
			charMap.get(v).add(u);
		}

		for (int k = 0; k < m; k++) {
			int q = sc.nextInt();
			boolean[] visit = new boolean[n];
			Arrays.fill(visit, false);
			//System.out.print("q:" + q);
			String query_string = sc.nextLine().trim();
			//System.out.print(" str:" + query_string);

			String out_string = query_string.replaceFirst(c[q - 1] + "", "");
			//System.out.print(" " + c[q - 1] + ":" + out_string);
			visit[q - 1] = true;

			for (int i : charMap.get(q)) {
				if (!visit[i - 1]) {
					out_string = out_string.replaceFirst(c[i - 1] + "", "");
					visit[i-1]=true;
					//System.out.print(" " + c[i - 1] + ":" + out_string);
					for (int j : charMap.get(i)) {
						if (!visit[j - 1]) {
							out_string = out_string.replaceFirst(c[j - 1] + "", "");
							//System.out.print(" " + c[j - 1] + ":" + out_string);
							visit[j-1]=true;
						}
					}
				}

			}
			System.out.println(out_string.length());
		}

	}

}
