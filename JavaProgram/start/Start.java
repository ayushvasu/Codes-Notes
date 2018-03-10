package com.start;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Start {

	public static void main(String as[]) {

		List<Integer> asd = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13);

		Function<Integer, Predicate<Integer>> isGreater = pivot -> number -> number > pivot;

		asd.stream().filter(e -> e % 2 == 0).filter(isGreater.apply(4)).forEach(System.out::println);
		asd.stream().filter(a -> isPrime(a)).forEach(System.out::println);

	}

	static boolean isPrime(int a) {
		Predicate<Integer> isDivisible = divisor -> a % divisor == 0;

		return a > 1 && IntStream.range(2, a).noneMatch(index -> isDivisible.test(index));
	}

}
