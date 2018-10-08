package com.pack.java;

import java.util.HashMap;
import java.util.Map;

public class HashcodeEqualsTest {
	public static void main(String[] args) {
//		Adams adams1 = new Adams(24, "AAR");
//		Adams adams2 = new Adams(27, "AAR");
//		Adams adams3 = new Adams(29, "ABC");
		Adams adams4 = new Adams(29, "ABC");
		Adams adams5 = new Adams(29, "DEF");
		Map<Adams, String> map = new HashMap<>();
		
//		map.put(adams1, "Adams1");
//		map.put(adams2, "Adams2");
//		map.put(adams3, "Adams3");
		map.put(adams4, "Adams4");
		map.put(adams5, "Adams5");
		
		System.out.println(map.toString());
		
		String name = map.get(adams4);
		System.out.println(name);
		
		name = map.get(adams5);
        System.out.println(name);
	}
}

class Adams {
	
//	String operation;
	
	Integer age;
	String clazz;

	Adams(Integer age, String clazz) {
		this.age = age;
		this.clazz = clazz;
	}

	@Override
	public boolean equals(Object obj) {
//		System.out.println(operation);
		boolean result = false;
		if (obj instanceof Adams) {
			Adams incomingAdams = (Adams) obj;
			result = incomingAdams.age.equals(this.age) && incomingAdams.clazz.equals(this.clazz);
		}
		return result;
	}

	@Override
	public int hashCode() {
//		System.out.println(operation);
		return (this.age + this.clazz.length()) * 13;
//	    return this.age * 13;
	}
	
	@Override
		public String toString() {
			
			return this.age + ", " + this.clazz;
		}
}
