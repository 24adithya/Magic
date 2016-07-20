package com.pack.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
		list.addFirst("Adithya");
		list.addFirst("Adams");
		list.add(0, "abc");
		
		List<String> list2 = new ArrayList<>();
		list2.add(0, "abc");
		list2.add(0, "def");
		
		System.out.println(list);
		System.out.println(list2);
	}
}
