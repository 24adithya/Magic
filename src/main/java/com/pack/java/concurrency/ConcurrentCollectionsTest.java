package com.pack.java.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCollectionsTest {

	public static void main(String[] args) {
		ConcurrentCollectionsTest test = new ConcurrentCollectionsTest();
		
		//Test concurrent map
		test.testConcurrentMap();
		
		//Test copy on write list
		test.testCopyOnWriteList();
	}

	private void testConcurrentMap() {
		// Map<String, Object> map = new HashMap<>();
		Map<String, Object> map = new ConcurrentHashMap<>();
		map.put("flamingo", 10);
		map.put("duck", 20);

		for (String key : map.keySet()) {
			map.remove(key);

			System.out.println(map.toString());
		}
	}

	private void testCopyOnWriteList() {
		List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
		for (Integer item : list) {
			System.out.print(item + " ");
			list.add(9);
		}
		System.out.println();
		System.out.println("Size: " + list.size());
		
		for (Integer item : list) {
			System.out.print(item + " ");
//			list.add(9);
		}
	}
}
