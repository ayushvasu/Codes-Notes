package com.start;
import java.util.Arrays;

public class CoinChange {
	public static void main(String[] ar) {
		int[] coin = { 1, 2, 5, 10 };
		int total = 55;
		getChange(coin,total);
	}

	static void getChange(int[] coin, int sum) {
		int[] arrSum = new int[sum+1];
		Arrays.fill(arrSum, Integer.MAX_VALUE-2);
		int[] ittCoin = new int[sum+1];
		Arrays.fill(ittCoin,-1);
		arrSum[0] = 0;
		for(int j = 0;j<coin.length;j++)
		{
			for(int i=1;i<=sum;i++){
				//System.out.println("sum::"+i);
				if(coin[j]<=i){
					if(arrSum[i] > 1+arrSum[i-coin[j]]){
						arrSum[i] = 1+arrSum[i-coin[j]];
						ittCoin[i] = j;
					}
				}
			}
		}
		System.out.println("Minimum Number of coin "+arrSum[sum]);
		printChange(ittCoin,coin);
	}
	

	static void printChange(int[] pntCoin,int[] coin) {
		System.out.println("Coins Are");
		int i = pntCoin.length - 1;
		while(i>0){
			int coinVal = coin[pntCoin[i]];
			System.out.print(coinVal+"  ");
			i = i - coinVal; 
		}
	}
}
