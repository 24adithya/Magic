package com.pack.concurrent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		ExecutorsTest executorsTest = new ExecutorsTest();
//		MyRunnable r = executorsTest.new MyRunnable();
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
		long nowTime = System.currentTimeMillis();
		Date begDate = new Date(nowTime);
		System.out.println(begDate);
		
		Callable<String> c1 = executorsTest.new Reading();
		Callable<String> c2 = executorsTest.new Painting();
		Callable<String> c3 = executorsTest.new Cooking();

		List<Callable<String>> tasks = new ArrayList<>();
		tasks.add(c1);
		tasks.add(c2);
		tasks.add(c3);
		
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println("No. of processors " + processors);
//		ExecutorService ex = Executors.newFixedThreadPool(processors);
		ExecutorService ex = Executors.newSingleThreadExecutor();
		
//		ExecutorService ex = Executors.newFixedThreadPool(3);
		List<Future<String>> results = ex.invokeAll(tasks);
		ex.shutdown();
		for(Future<String> result : results)
		{
			System.out.println(result.get());
		}
		nowTime = System.currentTimeMillis();
		Date endDate = new Date(nowTime);
		System.out.println(endDate);
		System.out.println("Time taken = " + (endDate.getTime() - begDate.getTime())/1000);
		System.out.println("Main continues !");	
		
		System.out.println("------------------Traditional------------------");
		
		nowTime = System.currentTimeMillis();
		begDate = new Date(nowTime);
		System.out.println(begDate);
		
		read();
		cook();
//		paint();
		
		nowTime = System.currentTimeMillis();
		endDate = new Date(nowTime);
		System.out.println(endDate);
		
		System.out.println("Time taken = " + (endDate.getTime() - begDate.getTime())/1000);
		
//		Callable<Integer> c = executorsTest.new MyCallable();
//		ExecutorService ex = Executors.newCachedThreadPool();
//		Future<Integer> f = ex.submit(c); // finishes in the future
//		ex.shutdown();
		
		
		
		/*System.out.println("'main' executing");
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
		}*/
	}
	
	public static String paint() throws Exception
	{
		throw new Exception("Unable to paint !");
//		long i = 1;
//		for (; i <= 999999l; i++)
//		{
//			i += 1;
//			System.out.println("Painted " + i + " paintings !");
////			Thread.sleep(5000l);
//		}
//		return "Painted " + i + " paintings !";
	}
	
	public static String cook() throws InterruptedException
	{
		long i = 1;
		for (; i <= 999999l; i++)
		{
			i += 1;
			System.out.println("Cooked " + i + " emparedados !");
//			Thread.sleep(5000l);
		}
		return "Cooked " + i + " items !";
	}
	
	public static String read()
	{
		long i = 1;
		for (; i <= 999999l; i++)
		{
			i += 1;
			System.out.println("Read " + i + " books !");
//			Thread.sleep(5000l);
		}
		return "Read " + i + " chapters !";
	}

	/*class MyRunnable implements Runnable {

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
			long count = ThreadLocalRandom.current().nextInt(1, 11);
			for (long i = 1; i <= count; i++)
			{
				System.out.println("Running..." + i);
			}
			return count;
		}
	}*/
	
	class Cooking implements Callable<String> {
		@Override
		public String call() throws InterruptedException
		{
			long i = 1;
			for (; i <= 999999l; i++)
			{
				i += 1;
				System.out.println("Cooked " + i + " emparedados !");
//				Thread.sleep(5000l);
			}
			return "Cooked " + i + " items !";
		}
	}
	
	class Reading implements Callable<String> {
		@Override
		public String call()
		{
			long i = 1;
			for (; i <= 999999l; i++)
			{
				i += 1;
				System.out.println("Read " + i + " books !");
//				Thread.sleep(5000l);
			}
			return "Read " + i + " chapters !";
		}
	}
	
	class Painting implements Callable<String> {
		@Override
		public String call() throws Exception
		{
			throw new Exception("Unable to paint !");
//			long i = 1;
//			for (; i <= 999999l; i++)
//			{
//				i += 1;
//				System.out.println("Painted " + i + " paintings !");
////				Thread.sleep(5000l);
//			}
//			return "Painted " + i + " paintings !";
		}
	}
}
