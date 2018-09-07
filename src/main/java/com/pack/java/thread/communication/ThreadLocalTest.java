package com.pack.java.thread.communication;

public class ThreadLocalTest {

	public static void main(String[] args) {
		MyThread1 mt1 = new MyThread1("A");
		MyThread1 mt2 = new MyThread1("B");
		MyThread1 mt3 = new MyThread1("C");
		mt1.start();
		mt2.start();
		mt3.start();
	}
}

class MyThread1 extends Thread {
	private static ThreadLocal tl = new ThreadLocal() {
		protected synchronized Object initialValue() {
			return new Integer(sernum++);
		}
		
		/*protected synchronized set(Object value) {
			sernum++;
		}*/
	};
	private static int sernum = 100;

	MyThread1(String name) {
		super(name);
	}

	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.println(getName() + " " + tl.get());
	}
}
