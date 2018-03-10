package com.start;
import java.util.HashSet;


public class LinearSumPair {

	public static void main(String[] ar){
		int[] a = {1,2,3,4,5};
		int sum = 8;
		Boolean as = findPair(a,sum);
		if(as){System.out.println("YO YO");}else{System.out.println("Honey");}
	
	}
	public static Boolean findPair(int[] a,int sum){
		HashSet<Integer> set=new HashSet<Integer>();  
		for(int i:a){
			//System.out.print(i);
			int comp = sum -i;
			if(set.contains(i)){System.out.println(i+"+"+comp);return true;}
			set.add(comp);
		}
		return false;
	}
	
}
