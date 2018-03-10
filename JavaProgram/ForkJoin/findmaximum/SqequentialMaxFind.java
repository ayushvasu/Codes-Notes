package com.findmaximum;

public class SqequentialMaxFind {
	
	public int sqequentialMaxFind(int[] num,int highIndex) {
		
		int max = num[0];
		for(int i=0;i<highIndex;i++)
			if(max < num[i])
				max = num[i];
		
		return max;
	}

}
