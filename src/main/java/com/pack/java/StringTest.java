package com.pack.java;

import java.util.ArrayList;
import java.util.List;

public class StringTest {

	public static void main(String[] args) {
//		String string = "animals";
//		System.out.println(string.substring(3)); // mals
//		System.out.println(string.substring(string.indexOf('m'))); // mals
//		System.out.println(string.substring(3, 4)); // m
//		System.out.println(string.substring(3, 7)); // mals
//		StringBuilder sb = new StringBuilder("start");
//		sb.append("+middle"); // sb = "start+middle"
//		StringBuilder same = sb.append("+end");
//		System.out.println(sb);
//		sb.substring(0,5);
//		System.out.println(sb);
//		int ids[], types;
//		
//		List<StringTest> one = new ArrayList<>();
//		List<StringTest> two = new ArrayList<>();
//		StringTest test1 = new StringTest();
//		StringTest test2 = new StringTest();
//		one.add(test1);
//		two.add(test1);
//		System.out.println(one.equals(two));
		
		String str = "     Diff Qty Dth    ";
		System.out.println(str.replaceAll("\\s+",""));
		
		String column = "DiffQtyDth";
		StringBuilder title = new StringBuilder();
		for(int count = 0; count < column.length() ; count++) {
			if(Character.isUpperCase( column.charAt(count) )) {
				title.append(" ").append(column.charAt(count));
			}
			else {
				title.append(column.charAt(count));
			}
		}
		
		System.out.println(title.toString().trim());
	}

}
