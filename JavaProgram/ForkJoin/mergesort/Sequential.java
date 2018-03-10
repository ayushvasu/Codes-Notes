package com.mergesort;

import java.util.Arrays;

public class Sequential {
	public void mergeSort(int[] num) {
		if(num.length <= 1)
			return;
		
		int middleIndex = num.length / 2;
		int[] left = Arrays.copyOfRange(num, 0, middleIndex);
		int[] right = Arrays.copyOfRange(num, middleIndex, num.length);
		
		mergeSort(left);
		mergeSort(right);
		
		merge(left,right,num);
	}

	public void merge(int[] left, int[] right, int[] num) {
		int i=0;
		int j=0;
		int k=0;
		while(i<left.length && j<right.length) {
			if(left[i] <right[j])
				num[k++] = left[i++];
			else
				num[k++] = right[j++];
			
		}
		while(i<left.length)
			num[k++] = left[i++];
		while(j<right.length)
			num[k++] = right[j++];
	}

}
