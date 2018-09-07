package com.pack.java.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsymmetricSetTest {

	public static void main(String[] args) {
		AsymmetricSetTest test = new AsymmetricSetTest();
		test.testAsymmetricTest();
	}

	private void testAsymmetricTest() {
		Set<Integer> s1 = Stream.iterate(10, t -> t + 1).limit(5).collect(Collectors.toCollection(TreeSet::new));
		Set<Integer> s2 = Stream.iterate(13, t -> t + 1).limit(5).collect(Collectors.toCollection(TreeSet::new));
		
		s1.forEach(System.out::println);
		System.out.println();
		s2.forEach(System.out::println);
		
		Set<Integer> exclusionSet = new TreeSet<>(s1);
		exclusionSet.addAll(s2);
		
		Set<Integer> commonSet = new TreeSet<>(s2);
		commonSet.retainAll(s1);
		
		exclusionSet.removeAll(commonSet);
		System.out.println();
		exclusionSet.forEach(System.out::println);
	}
}
