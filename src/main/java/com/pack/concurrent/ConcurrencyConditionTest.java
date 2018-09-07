package com.pack.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrencyConditionTest {

	public static void main(String[] args) {
		
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		Map<String, String> map = new HashMap<>(); 
		
		Supplier s = new Supplier(lock, condition, map);
		Manufacturer m = new Manufacturer(lock, condition, map);
		
		Thread supplierThread = new Thread(s);
		Thread manufacturerThread = new Thread(m);
		
		manufacturerThread.start();
		supplierThread.start();
	}
}

class Supplier implements Runnable {

	Lock lock;
	Condition condition;
	Map<String,String> map;
	
	public Supplier(Lock lock, Condition condition, Map<String, String> map) {
		super();
		this.lock = lock;
		this.condition = condition;
		this.map = map;
	}

	@Override
	public void run() {
		boolean isLockAcq = false;
		try{
			isLockAcq = lock.tryLock(5, TimeUnit.SECONDS);
			if(isLockAcq) {
				System.out.println("Supplying materials to Manufacturer");
				condition.signal();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(isLockAcq) {
				lock.unlock();
			}
		}
	}
}

class Manufacturer implements Runnable {

	Lock lock;
	Condition condition;
	Map<String,String> map;
	
	public Manufacturer(Lock lock, Condition condition, Map<String, String> map) {
		super();
		this.lock = lock;
		this.condition = condition;
		this.map = map;
	}

	@Override
	public void run() {
		boolean isLockAcq = false ;
		try{
//			while(!map.isEmpty()) {
				isLockAcq = lock.tryLock();
				if(isLockAcq) {
					System.out.println("Going to wait till supplier signals..");
					condition.await();
					System.out.println("Supplier has provided materials..");
				}
//			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally{
			if(isLockAcq) {
				lock.unlock();
			}
		}
	}
}