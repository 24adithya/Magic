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
		// this(10);
		System.out.println("Inside contrcutor 1");
	}

	public static void main(String[] args) {

		int x1 = 50, x2 = 75;
		boolean b = x1 >= x2;
		if (b = true)
			System.out.println("Success");
		MainVarargsTest test = new MainVarargsTest();
		test.admission();
	}

	{
		System.out.println("Inside block 1");
	}

	static {
		System.out.println("Inside static block 1");
	}

	public void admission() {
		int amount = 0b101;
//		int amount = 0xE;
//		double amount = 0xE;
		System.out.println(amount);
	}
}

