package com.sort;

import java.util.*;

public class SortByFrequency {

	public static void main(String ar[]) {
		String[] arr = { "2", "4", "4", "4", "5", "8", "8", "4", "7", "1"};
		Arrays.sort(arr,new FreComprator(arr));
		System.out.println(Arrays.toString(arr));
	}
}

class FreComprator implements Comparator<String> {

	Map<String, Integer> fmap = new HashMap<String, Integer>();

	public FreComprator(String[] arr) {
		for (int i = 0; i < arr.length; i++)
			fmap.put(arr[i], fmap.get(arr[i]) == null ? 1 : fmap.get(arr[i]) + 1);
	}

	@Override
	public int compare(String arg0, String arg1) {
		if (fmap.get(arg0) == fmap.get(arg1))
			return arg0.compareTo(arg1);
		else
			return fmap.get(arg0) < fmap.get(arg1) ? 1 : -1;
	}

}
