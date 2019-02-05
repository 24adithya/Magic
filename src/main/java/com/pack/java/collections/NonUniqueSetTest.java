package com.pack.java.collections;

import java.util.HashSet;
import java.util.Set;

public class NonUniqueSetTest {

	public static void main(String[] args) {

		NonUniqueSetTest test = new NonUniqueSetTest();

		Set<MyKey> set = new HashSet<>();
		set.add(test.new MyKey(10, "AAR"));
		set.add(test.new MyKey(10, "AAR"));

		set.forEach(System.out::println);
	}

	private class MyKey {
		private int number;
		private String name;

		public MyKey(int number, String name) {
			super();
			this.number = number;
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.number + " " + this.name;
		}
	}
}
