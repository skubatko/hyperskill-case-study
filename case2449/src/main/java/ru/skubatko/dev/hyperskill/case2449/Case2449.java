package ru.skubatko.dev.hyperskill.case2449;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Case2449 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		proceed(sc.nextLine()).forEach(System.out::println);
	}

	static List<String> proceed(String s) {
		return Arrays.stream(s.toLowerCase().replaceAll("[^a-z0-9\\s]", "").split("\\s"))
				.collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting())).entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(10)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
}
