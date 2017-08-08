package com.pack.java;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("1" + null, "Adithya");
		map.put("1" + "abc", "Narayan");
		
		System.out.println(map.get("1" + null));
		System.out.println(map.get("1" + "abc"));
	}

}
