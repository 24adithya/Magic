package com.pack.java.collections;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TreeMapTest {

	public static void main(String[] args) {
		List<String> stringList = new LinkedList<>();

		Map<String, String> stringMap = new LinkedHashMap<>();

		// First record
		stringMap.put("-1|100", "a");
		stringMap.put("1|100", "b");
		stringMap.put("2|100", "c");
		stringMap.put("null|100", "d");

		// second record
		stringMap.put("-1|200", "a1");
		stringMap.put("1.1|200", "b1");
		stringMap.put("2.1|200", "c1");
		stringMap.put("null|200", "d1");
		
		System.out.println(stringMap.values().toString());
	}

}
