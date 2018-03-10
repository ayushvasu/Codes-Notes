package com.start;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Start2 {

	public static void main(String ar[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		HashMap<String, LinkedList<PriceNRate>> phoneMap = new HashMap<>();
		String phonereg = "(.*)\\s+(\\d+)\\s+(\\d+)";
		Pattern preg = Pattern.compile(phonereg);
		for (int i = 0; i < n; i++) {
			String phone = sc.nextLine();
			//System.out.println("    " + phone);
			Matcher mat = preg.matcher(phone);
			if (mat.find()) {
				String key = mat.group(1).replaceAll("\\s+", ",");
				PriceNRate val = new PriceNRate(Long.parseLong(mat.group(2)), Long.parseLong(mat.group(3)));
				if (!phoneMap.containsKey(key))
					phoneMap.put(key, new LinkedList<PriceNRate>());
				phoneMap.get(key).add(val);
			}
		}
		//System.out.println(phoneMap);
		int q = sc.nextInt();
		sc.nextLine();
		String phonereg_q = "(.*)\\s+(\\d+)";
		Pattern preg_q = Pattern.compile(phonereg_q);
		for(int j=0;j<q;j++) {
			//System.out.println("q:"+j);
			String phone = sc.nextLine();
			//System.out.println("    " + phone);
			Matcher mat = preg_q.matcher(phone);
			if (mat.find()) {
				String key = mat.group(1).replaceAll("\\s+", ",");
				//System.out.println("    key::::" + key);
				if(phoneMap.containsKey(key)) {
					Long budget = Long.parseLong(mat.group(2));
					Long rate = 0L;
					Long price = 0L;
					for(PriceNRate o:phoneMap.get(key)) {
						if(o.price <= budget && (o.rate > rate || (o.rate == rate && o.price < price)))
						{
							rate = o.rate;
							price = o.price;
						}
					}
					System.out.println(price);
				}else {
					System.out.println("-1");
				}
				
			}
		}
		
	}

}

class PriceNRate {
	Long price;
	Long rate;

	PriceNRate(Long price, Long rate) {
		this.price = price;
		this.rate = rate;
	}
	
	public String toString() {
		return price+":"+rate;
	}

}
