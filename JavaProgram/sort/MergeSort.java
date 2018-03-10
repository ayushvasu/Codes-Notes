package com.sort;

import java.util.Arrays;

public class MergeSort {

	static int[] mergeSort(int[] arr) {
		if (arr.length < 2)
			return arr;
		else {
			int[] leftArray = Arrays.copyOfRange(arr, 0, arr.length / 2);
			int[] rightArray = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
			leftArray = mergeSort(leftArray);
			rightArray = mergeSort(rightArray);
			arr = mergeArray(leftArray, rightArray);
			return arr;
		}

	}

	static int[] mergeArray(int[] arr1, int[] arr2) {
		int[] arr = new int[arr1.length + arr2.length];
		int k = 0;
		int i = 0, j = 0;
		for (i = 0, j = 0; i < arr1.length && j < arr2.length;) {
			if (arr1[i] < arr2[j])
				arr[k++] = arr1[i++];
			else
				arr[k++] = arr2[j++];
		}
		if (j < arr2.length)
			while (j < arr2.length)
				arr[k++] = arr2[j++];
		else if (i < arr1.length)
			while (i < arr1.length)
				arr[k++] = arr1[i++];
		return arr;
	}

	public static void main(String ar[]) {
		int[] arr = { 123, 43, 123, 66, 12, 554, 11 };
		System.out.println("Array before Sort :" + Arrays.toString(arr));
		arr = mergeSort(arr);
		System.out.println("Array after Sort :" + Arrays.toString(arr));
	}

}
