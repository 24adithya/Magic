package com.pack.java;

import java.util.HashMap;
import java.util.Map;

public class MultipleValueMap {

	public static void main(String[] args)
	{
		String key = "" + 120 + 130;
		
		Map<Integer, RollUpCategoryAttributes> map = new HashMap<>();
		
		RollUpCategoryAttributes attr1 = new RollUpCategoryAttributes();
		RollUpCategoryAttributes attr2 = new RollUpCategoryAttributes();
		
		map.put(10, attr1);
		map.put(10, attr2);
		
		System.out.println(map);
	}
}
