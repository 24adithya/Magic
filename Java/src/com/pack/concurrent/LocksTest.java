package com.pack.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksTest {
	
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		ActivityClass activity = new ActivityClass(lock);
		
		Worker1 w1 = new Worker1(activity);
		Thread t1 = new Thread(w1, "Adams");
		Thread t2 = new Thread(w1, "Edge");
		
		Worker2 w2 = new Worker2(activity);
		Thread t3 = new Thread(w2, "Adams");
		Thread t4 = new Thread(w2, "Edge");
		
		t1.start();
		t2.start();
		/*t3.start();
		t4.start();*/
	}
}

class ActivityClass {
	
	Lock lock;
	
	public ActivityClass(Lock lock) {
		super();
		this.lock = lock;
	}

	public void method1() {
//		lock.lock();//Done testing
		boolean isLockAcquired;
		try {
			isLockAcquired = lock.tryLock(5,TimeUnit.MILLISECONDS);
		 
			try {
				if(isLockAcquired) {
					for(int count = 1 ; count < 10 ; count++) {
						System.out.println("Thread " + Thread.currentThread().getName() + " prints info in method 1");
					}
				}
			}
			finally {
				if(isLockAcquired) {
					lock.unlock();
				}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void method2() {
		for(int count = 1 ; count < 10 ; count++) {
			System.out.println("Thread " + Thread.currentThread().getName() + " prints info in method 2");
		}
	}
}

class Worker1 implements Runnable {

	ActivityClass activity;
	
	public Worker1(ActivityClass activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void run() {
		activity.method1();
	}
}

class Worker2 implements Runnable {

	ActivityClass activity;
	
	public Worker2(ActivityClass activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void run() {
		activity.method2();
	}
}