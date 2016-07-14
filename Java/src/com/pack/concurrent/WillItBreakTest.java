package com.pack.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WillItBreakTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		
		List<Worker> workers = new ArrayList<>();
		Worker worker = null;
		for(int  i = 0; i < 1000000l ; i++)
		{
			worker = new Worker();
			workers.add(worker);
		}
		List<Future<String>> results = executorService.invokeAll(workers);
		executorService.shutdown();
		
		for(Future<String> result : results)
		{
			System.out.println(result.get());
		}
		
	}
}

class Worker implements Callable<String>
{

	static long count = 0;
	@Override
	public String call() throws Exception {

		Vector<Integer> alphabets = fetchAlphabets();
		
		int size = 0;
		try
		{
			size = alphabets.elementAt(0);
		}
		catch(ArrayIndexOutOfBoundsException | NullPointerException e)
		{
			System.out.println("Gotcha ! " + ++count);
		}
		
		return "Added " + size  + " to the vector"; 
		
	}
	
//	private Vector<Integer> fetchAlphabets()
//	{
//		Vector<Integer> alphabets = new Vector<>();
//		int i = 0;
//		for(;i < 99999999999l ; i++)
//		{
//			alphabets.add(i);
//		}
//		
//		return alphabets;
//	}
	
	private Vector<Integer> fetchAlphabets()
	{
		int num = (int)(Math.random() * 2);
		if(num != 2)
			return null;
		else 
			return new Vector();	
	}
}


