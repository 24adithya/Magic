package com.pack.java.thread.communication;

import java.util.LinkedList;
import java.util.List;

import com.pack.java.MainVarargsTest;

public class VolatileTest {
	
	private List<Integer> numberCollection;
	
	{
		numberCollection = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		
		VolatileTest volatileTest = new VolatileTest();
		volatileTest.executeTask();
	}
	
	protected void executeTask() {
		Thread[] threadArray = new Thread[2];
//		Runnable inserter = () -> {
//			VolatileTest volatileTest;
//			volatileTest.insertIntoColl(number);
//		};
		threadArray[0] = new Thread();
	}
	
	protected void insertIntoColl(int number) {
		System.out.println();
	}
}

class Inserter implements Runnable {
	
	private VolatileTest volatileTest;
	public Inserter(VolatileTest volatileTest) {
		this.volatileTest = volatileTest;
	}
	
	@Override
	public void run() {
		volatileTest.insertIntoColl();
	}
}