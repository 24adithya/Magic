package com.pack.java;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class EqualsTest {

	public static void main(String[] args)
	{
		PriorityQueue<String> queue = new PriorityQueue<>();
		
		String[] elements = new String[]{"a","g","z","c"};
		
		/*for(String str : elements)
		{
			queue.offer(str);
		}*/
		
		/*queue.offer("a");
		queue.offer("g");
		queue.offer("z");
		queue.offer("c");*/
		
		queue.add("a");
		queue.add("g");
		queue.add("z");
		queue.add("c");
		
		for(int cnt = 0; cnt < elements.length ; cnt++)
		{
			System.out.println(queue.poll());
		}
		
		
		/*System.out.println(queue.peek());
		System.out.println(queue.poll());*/
		
		/*TreeMap<String, String> map = new TreeMap<>();
		map.put("a", "aaa");
		map.put("d", "ddd");
		map.put("g", "ggg");
		map.put("j", "jjj");
		map.put("o", "ooo");
		
		System.out.println(map.headMap("d"));
		System.out.println(map.headMap("d", true));
		System.out.println(map.tailMap("d"));
		System.out.println(map.tailMap("d", false));
		
		System.out.println("\nSubmap = " + map.subMap("d", false, "j", false));*/
		
		/*Map<Dog, String> map = new HashMap<>();
		Dog d1 = new Dog("clover");
		map.put(d1, d1.name);
		
		d1 = new Dog("arthur");
		map.put(d1, d1.name);
		
		System.out.println(map.get(new Dog("clover")));
		System.out.println(map.get(new Dog("arthur")));*/
	}
}

class Dog {
	
	String name;
	
	public Dog(String name) {
		this.name = name;
	}

	@Override
	public int hashCode()
	{
		return name.length();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		/*if(obj instanceof Dog)
		{
			return ((Dog)obj).name.equals(this.name);
		}
		return false;*/
		return true;
	}
}
