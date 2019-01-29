package com.pack.java.datastructures;

import java.util.HashMap;
import java.util.Map;

public class HashingFunctionTest {

	public static void main(String[] args) {
		HashingFunctionTest test = new HashingFunctionTest();
		System.out.println(10 ^ (10 >>> 16));
//		test.testXORing();
		test.testEntryLinkedList();
	}

	private void testEntryLinkedList() {
		Map<MyEntry, String> map = new HashMap<>();
		
		MyEntry entry1 = new MyEntry();
		entry1.setName("AAR1");
		
		MyEntry entry2 = new MyEntry();
		entry2.setName("AAR1");
		
		MyEntry entry3 = new MyEntry();
		entry3.setName("AAR22");//Giving different length to generate different hashcode
		
		map.put(entry1, "AAR10");
		map.put(entry2, "AAR11");
		map.put(entry3, "AAR12");
		
		map.entrySet();
	}

	private void testXORing() {
		Integer h = 10;
		System.out.println(h.hashCode());
		System.out.println(h.hashCode() >> 16);
		System.out.println(h.hashCode());
		System.out.println(h.hashCode() >>> 16);
		System.out.println(h.hashCode() ^ 4);
		System.out.println(h.hashCode() ^ h.hashCode() >>> 16);
	}
}

class MyEntry {
	private String name;
	
	@Override
	public int hashCode() {
		return name.length() * 7;
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}