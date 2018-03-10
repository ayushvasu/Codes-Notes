package com.start;
import java.util.Scanner;

//764A
public class NumberOfComradeKill {

	public static void main(String ar[]) {
		Scanner s = new Scanner(System.in);
		int min_call = s.nextInt();
		int min_artist = s.nextInt();
		int min_day = s.nextInt();
		int lcm = findLCM(min_call, min_artist);
		int ans = min_day / lcm;
		System.out.println("" + ans);
		s.close();
	}

	public static int findLCM(int a, int b) {
		int l = a > b ? a : b;
		int lcm = 1;
		for (int i = l; i <= a * b; i++) {
			if (i % a == 0 && i % b == 0) {
				lcm = i;
				break;
			}
		}
		return lcm;
	}
}
