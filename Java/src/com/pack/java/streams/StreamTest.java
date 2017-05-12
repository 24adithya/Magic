package com.pack.java.streams;

import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
		StreamTest streamTest = new StreamTest();
		streamTest.testSimpleStreamCreation();
	}

	private void testSimpleStreamCreation() {
		Stream<Double> randoms = Stream.generate(Math::random);
		Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
		
//		randoms.forEach(System.out::println);
		oddNumbers.forEach(System.out::println);
	}
}
