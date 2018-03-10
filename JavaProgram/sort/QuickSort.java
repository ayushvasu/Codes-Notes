package com.sort;

import java.util.Arrays;

public class QuickSort {

	static int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	static void sort(int arr[], int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}

	public static void main(String ar[]) {
		int[] arr = { 3,4,1,2,5 };
		partition(arr, 0, arr.length-1);
		System.out.println("Array before Sort :" + Arrays.toString(arr));
		sort(arr, 0, arr.length-1);
		System.out.println("Array after Sort :" + Arrays.toString(arr));
	}

}
