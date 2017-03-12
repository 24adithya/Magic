package com.pack.java;

class Pa {
	public Pa() {
		System.out.println("P");
	}
}

public class MainVarargsTest extends Pa {
	
	int $ = 10, _ = 20;
	
	{
		System.out.println("Inside block 2" + $ + _);
	}
	static {
		System.out.println("Inside static block 2");
	}
	
	public MainVarargsTest(int no) {
		System.out.println("Inside param contrcutor 1");
	}
	
	public MainVarargsTest() {
//		this(10);
		System.out.println("Inside contrcutor 1");
	}
	
	public static void main(String[] args) {
//		int x = 1;
//		double y = 33;
//		long z = x * y;
		
		double a = 1_234.0;
		MainVarargsTest test = new MainVarargsTest();
	}
	{
		System.out.println("Inside block 1");
	}
	
	static {
		System.out.println("Inside static block 1");
	}
}
