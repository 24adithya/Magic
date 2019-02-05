package com.pack.java.collections;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListTest {

	public static void main(String[] args) {

		List<Integer> list = new CopyOnWriteArrayList<>();
		list.add(10);
		list.add(20);
		
		Iterator<Integer> itr = list.iterator();
		int count = 20;
		while(itr.hasNext()) {
			System.out.println(itr.next());
			list.add(++count);
		}
		
		list.forEach(System.out::println);
	}

}
