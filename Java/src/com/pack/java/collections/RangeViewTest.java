package com.pack.java.collections;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RangeViewTest {

	public static void main(String[] args) {
		List<Integer> l1 = Stream.iterate(10, t -> t + 1).limit(5).collect(Collectors.toList());
		l1.forEach(System.out::println);
		List<Integer> l2 = l1.subList(0, l1.size() - 1);
		l2.forEach(System.out::println);
	}
}
