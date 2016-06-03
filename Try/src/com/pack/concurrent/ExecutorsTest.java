package com.pack.concurrent;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ExecutorsTest {

	public static void main(String[] args)
	{
		ExecutorsTest executorsTest = new ExecutorsTest();
		MyRunnable r = executorsTest.new MyRunnable();
		/*
		 * Executors.newCachedThreadPool(); Executors.newFixedThreadPool(5);
		 * 
		 * 
		 * ScheduledExecutorService ftses = Executors.newScheduledThreadPool(4);
		 * // multi-threaded // version
		 * System.out.println("Time before scheduling : " + new Date()); //
		 * ftses.schedule(r, 5, TimeUnit.SECONDS); // run once after // a delay
		 * ftses.scheduleAtFixedRate(r, 2, 5, TimeUnit.SECONDS); // begin after
		 * a // 2sec delay // and begin again every 5 seconds
		 * ftses.scheduleWithFixedDelay(r, 2, 5, TimeUnit.SECONDS);
		 */

		Callable<Integer> c = executorsTest.new MyCallable();
		ExecutorService ex = Executors.newCachedThreadPool();
		Future<Integer> f = ex.submit(c); // finishes in the future
		
		ex.shutdown();
		
		System.out.println("'main' executing");
		try
		{
			ex.awaitTermination(10, TimeUnit.SECONDS);
			Integer v = f.get(); // blocks until done
			System.out.println("Ran:" + v);
			
			//Repeat
			f = ex.submit(c);
			v = f.get(); // blocks until done
			System.out.println("Ran:" + v);
		}
		catch (InterruptedException | ExecutionException iex)
		{
			System.out.println("Failed");
		}
	}

	class MyRunnable implements Runnable {

		@Override
		public void run()
		{
			System.out.println("Time before execution : " + new Date());
			System.out.println("Hii..ii");
		}

	}

	class MyCallable implements Callable<Integer> {
		@Override
		public Integer call()
		{
			// Obtain a random number from 1 to 10
			int count = ThreadLocalRandom.current().nextInt(1, 11);
			for (int i = 1; i <= count; i++)
			{
				System.out.println("Running..." + i);
			}
			return count;
		}
	}
}
