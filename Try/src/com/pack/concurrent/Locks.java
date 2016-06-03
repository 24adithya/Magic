package com.pack.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Locks {

	public static void main(String[] args)
	{
		Locks locks = new Locks();
		MyRunnable runnable = locks.new MyRunnable();
		//Thread[] threadArray = new Thread[10];
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Random random = new Random();
		
		locks.integers.add(random.nextInt((10000 - 0) + 1) + 0);
		
		for(int count = 0 ; count < 10 ; count++)
		{
			/*threadArray[count] = new Thread(runnable, "Counter" + count);
			threadArray[count].start();
			*/
			executorService.execute(runnable);
		}
		
		for(int count = 0 ; count < 10000 ; count++)
		{
			locks.add(random.nextInt((10000 - count) + 1) + count);
		}
	}

	private ConcurrentLinkedQueue<Integer> integers = new ConcurrentLinkedQueue<>();
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	public void add(Integer i)
	{
//		rwl.writeLock().lock(); // one at a time
		try
		{
			integers.add(i);
		}
		finally
		{
//			rwl.writeLock().unlock();
		}
	}

	public int findMax()
	{
	
//		rwl.readLock().lock(); // many at once
		try
		{
			return Collections.max(integers);
		}
		finally
		{
//			rwl.readLock().unlock();
		}
	}
	
	class MyRunnable implements Runnable
	{

		private List<Integer> integers = new ArrayList<>();
		
		@Override
		public void run()
		{
			System.out.println("Max no is = " + findMax());
		}
		
	}
}
