package com.pack.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter(); // the shared object
		IncrementerThread it1 = new IncrementerThread(counter);
		IncrementerThread it2 = new IncrementerThread(counter);
		it1.start(); // thread 1 increments the count by 10000
		it2.start(); // thread 2 increments the count by 10000
		it1.join(); // wait for thread 1 to finish
		it2.join(); // wait for thread 2 to finish
		System.out.println(counter.getValue()); // rarely 20000
		// lowest 11972
	}
}

class IncrementerThread extends Thread {
	private Counter counter;

	// all instances are passed the same counter
	public IncrementerThread(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		// "i" is local and thread-safe
		for (int i = 0; i < 10000; i++) {
			counter.increment();
		}
	}
}

class Counter {
	private AtomicInteger count = new AtomicInteger();

	public void increment() {
		count.getAndIncrement(); // atomic operation
	}

	public int getValue() {
		return count.intValue();
	}

	/*
	 * private int count;
	 * 
	 * public void increment() { count++; // it's a trap! // a single "line" is
	 * not atomic }
	 * 
	 * public int getValue() { return count; }
	 */
}