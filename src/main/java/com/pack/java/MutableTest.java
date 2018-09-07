package com.pack.java;

import java.util.ArrayList;
import java.util.List;

public final class MutableTest {

	private final List<String> list;

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		MutableTest test = new MutableTest(list);
		test.getList().add("d");
		test.print();

		test.<String>display("");
		test.<String[]>display(new String[] { "" });
		
		System.out.println("index of : " + test.findIndex());
		System.out.println("last index of : " + test.findLastIndex());
	}

	private MutableTest(List<String> list) {
		this.list = list;
	}

	public List<String> getList() {
		return list;
	}

	public void print() {
		System.out.println(list.toString());
	}

	<T> Integer display(T t) {
		return null;
	}

	int findIndex() {
		List<Integer> integerList = new ArrayList<>();
		integerList.add(10);
		integerList.add(20);
		integerList.add(10);
		
		return integerList.indexOf(10);
	}
	
	int findLastIndex() {
		List<Integer> integerList = new ArrayList<>();
		integerList.add(10);
		integerList.add(20);
		integerList.add(10);
		
		return integerList.lastIndexOf(10);
	}
	
	/*public static void addSound(List<? extends String> list) {
		list.add("quack");
	}
	
	public static void addSound1(List<?> list) {
		list.add("quack");
	}*/

}
