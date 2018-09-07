package com.pack.java;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitAndNotifyTest 
{
	public static void main(String[] args)
	{
		Thread.currentThread().setName("Adams");
		WaitAndNotifyTest.tryLock();
		/*ParentCounter parentCounter = new ParentCounter(4000, 6000);
		parentCounter.count(parentCounter);*/
	}
	
	private static void tryLock()
	{
		Lock l1 = new ReentrantLock();
		Lock l2 = new ReentrantLock();
		
		loop2: while (true)
		{
			boolean aq2 = l2.tryLock();
			boolean aq1 = l1.tryLock();
			try
			{
				if (aq1 && aq2)
				{
					System.out.println("Adithya");
					System.out.println("Adams");
					break loop2;
				}
			}
			finally
			{
				if (aq2)
				{
					l2.unlock();
					System.out.println("Released lock 2");
				}
				if (aq1)
				{
					l1.unlock();
					System.out.println("Released lock 1");
				}
			}
		}
	}
}

class ParentCounter implements Runnable
{
	private int initialCount, finalCount;
	
	public ParentCounter()
	{
		
	}
	
	public ParentCounter(int initialCount, int finalCount) {
		this.initialCount = initialCount;
		this.finalCount = finalCount;
	}
	
	public void count(ParentCounter parentCounter)
	{
		Thread counterThread = new Thread(this);
		counterThread.setName(ParentCounter.class.getCanonicalName());
		counterThread.start();

		WaitingCounter waitingCounter = new WaitingCounter(2000, 4000);
		waitingCounter.count(parentCounter, "2000_to_4000 Counter");
		
		RunningCounter runningCounter = new RunningCounter(0, 2000);
		runningCounter.count(parentCounter, "Zero_to_1000 Counter");
		
		synchronized(parentCounter)
		{
			try
			{
				System.out.println("Waiting for 'Waiting Counter' to finish..");
				parentCounter.wait();
				runningCounter = new RunningCounter(5000, 6000);
				counterThread = new Thread(runningCounter);
				counterThread.start();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName() + " executing..");
		for(int count = initialCount ; count <= finalCount ; count++)
		{
			System.out.print(count + " ");
		}
	}
}

class RunningCounter implements Runnable
{
	private ParentCounter parentCounter;
	private int initialCount, finalCount;
	public RunningCounter(int initialCount, int finalCount) {
		this.initialCount = initialCount;
		this.finalCount = finalCount;
	}
	
	public void count(ParentCounter parentCounter, String name)
	{
		this.parentCounter = parentCounter;
		
		Thread counterThread = new Thread(this);
		counterThread.setName(name);
		counterThread.start();
	}

	@Override
	public void run()
	{
		synchronized(parentCounter)
		{
			System.out.println("Thread " + Thread.currentThread().getName() + " executing..");
			for(int count = initialCount ; count <= finalCount ; count++)
			{
				System.out.print(count + " ");
			}
			System.out.println("About to notify '|Waiting Counter|'");
			parentCounter.notify();
		}
	}
}

class WaitingCounter implements Runnable
{
	private ParentCounter parentCounter;
	private int initialCount, finalCount;
	public WaitingCounter(int initialCount, int finalCount) {
		this.initialCount = initialCount;
		this.finalCount = finalCount;
	}
	
	public void count(ParentCounter parentCounter, String name)
	{
		this.parentCounter = parentCounter;
		
		Thread counterThread = new Thread(this);
		counterThread.setName(name);
		counterThread.start();
	}

	@Override
	public void run()
	{
		synchronized(parentCounter)
		{
			try
			{
				System.out.println("Waiting for running counter to finish..");
				parentCounter.wait();
				System.out.println("Thread " + Thread.currentThread().getName() + " executing..");
				for(int count = initialCount ; count <= finalCount ; count++)
				{
					System.out.print(count + " ");
				}
				System.out.println("About to notify '|Parent Counter|'");
				parentCounter.notify();

			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}