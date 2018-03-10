package com.start;

import java.util.Arrays;

public class ManchersAlgo {
	static int[] mancher(String str) {
		char[] sarr = str.toCharArray();
		int [] index = new int[str.length()];
		int c=0,r=0;
		for(int i=1;i<str.length()-1;i++) {
			int mirr = 2*c - i;
			
			if(i<r)
				index[i] = Math.min(index[mirr],r-i);
			
			while (sarr[i+(1+index[i])] == sarr[i-(1+index[i])])
				index[i]++;
			
			if(i + index[i] > r){
				c = i;
				r = i + index[i];
			}
		}
		System.out.println(Arrays.toString(index));		
		return index;
	}
	
	public static void main(String[] args) {
		String instr = "abc";
		StringBuilder str = new StringBuilder();
		str.append("^");
		for(char ch:instr.toCharArray()) {
			str.append("#");
			str.append(ch);
		}
		str.append("#");
		str.append("$");
		System.out.println(str.toString());
		int[] index = mancher(str.toString());
		System.out.println(Arrays.toString(index));		
	}

}
