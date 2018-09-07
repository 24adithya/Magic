package com.pack.java;

import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ArrayTest {

	public static void main(String[] args)
	{
		/*
		 * Integer[] a = new Integer[]{10, 20}; Integer[] b = new Integer[]{30,
		 * 40}; int[] c = null;
		 * 
		 * c = (int[]) Array.newInstance(Integer[].class.getComponentType(),
		 * a.length + b.length); System.arraycopy(a, 0, c, 0, a.length);
		 * System.arraycopy(b, 0, c, a.length, b.length);
		 * 
		 * System.out.println(c);
		 */
//		before();
//		Integer[] intArr = returnArr();
//		System.out.println(intArr.length);
		
		
		List<Integer> list = new ArrayList<>();
//		list.add(10);
		
		new ArrayTest().modifyList(list);
		
		System.out.println();
		
		Integer[] testInt = null;
		new ArrayTest().modifyArray(testInt);
		System.out.println();
	}
	
	private void modifyArray(Integer[] testInt)
	{
		testInt = new Integer[] {10,20,40,80};
		System.out.println(Arrays.toString(testInt));
	}

	private void modifyList(List<Integer> list) {
		list.add(20);
	}
	
	static Integer[] returnArr() {
		return new Integer[0];
	}

	public static void before()
	{
		Set set = new TreeSet();
		set.add("2");
		set.add(3);
		set.add("1");
		Iterator it = set.iterator();
		while (it.hasNext())
			System.out.print(it.next() + " ");
	}
}

class MapEQ {
	public static void main(String[] args)
	{
		
		Map<ToDos, String> m = new HashMap<ToDos, String>();
		ToDos t1 = new ToDos("Monday");
		ToDos t2 = new ToDos("Sunday");
		ToDos t3 = new ToDos("Tuesday");
		m.put(t1, "doLaundry");
		m.put(t2, "payBills");
		m.put(t3, "cleanAttic");
		System.out.println(m.size());
	}
}

class ToDos {
	String day;

	ToDos(String d) {
		day = d;
	}

	public boolean equals(Object o)
	{
		return ((ToDos) o).day.equals(this.day);
	}
	 public int hashCode() { return day.length(); }
}


class B1<T> {
	T[] t;
	
	public B1() {
		t = (T[])new Object[10];
	}
}

class P {
	protected int test(String test) {
		return 1;
	}
}

class C extends P {
	protected int test(String test) {
		return Integer.valueOf(10);
	}
}