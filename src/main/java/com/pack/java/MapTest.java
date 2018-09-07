package com.pack.java;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		Map<String, Integer> map1 = new HashMap<>();
	
		BiFunction<String, Integer, Integer> mapper = (k,v) -> v + 1;     
		Integer val = map1.computeIfPresent("tenmp", mapper);
		System.out.println(val);
	
		
		map.put("1" + null, "Adithya");
		map.put("1" + "abc", "Narayan");
		
		System.out.println(map.get("1" + null));
		System.out.println(map.get("1" + "abc"));
	}

}
