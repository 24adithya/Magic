package com.pack.java;

import java.util.ArrayList;
import java.util.List;

public class LegacyAndNonLegacyTest {

	public static void main(String[] args)
	{
		List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(20);
		
		legacyCode(list);
		
		for(Object obj : list)
		{
			System.out.println(obj);
		}
	}

	private static void legacyCode(List list)
	{
		list.add("Adithya");
	}
}
