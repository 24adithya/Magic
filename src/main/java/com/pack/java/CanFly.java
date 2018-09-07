package com.pack.java;

public abstract interface CanFly {
	public abstract void fly(int speed);

	public abstract void takeoff();

	public abstract double dive();
}

interface T1 {
	int test();

	default int defaultTest() {
		return 1;
	}
}

interface T2 {
	static boolean staticTest() {
		return false;
	}
}

interface T3 {

}

interface T4 extends T1, T2 {
	int defaultTest();
}

class T5 implements T4 {

	@Override
	public int test() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int defaultTest() {
		T2.staticTest();
		return 0;
	}

}

abstract class Bird {
	
	Integer number;
	
	private void fly() {
		System.out.println("Bird is flying");
	}

	public static void main(String[] args) {
		Bird bird = new Pelican();
		bird.fly();
		bird.testRuntimeException();
	}
	
	private void testRuntimeException() {
		try {
			System.out.println( Integer.valueOf(number) );
		}
		catch(NullPointerException npe) {
			System.out.println(npe.getMessage());
		}
	}
}

class Pelican extends Bird {
	protected void fly() {
		System.out.println("Pelican is flying");
	}
}
