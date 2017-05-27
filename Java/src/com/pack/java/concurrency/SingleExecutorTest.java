package com.pack.java.concurrency;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class SingleExecutorTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		SingleExecutorTest singleExecutorTest = new SingleExecutorTest();
//		singleExecutorTest.execute();
//			singleExecutorTest.executeWithCallable();
			
//			singleExecutorTest.invoke();
//			singleExecutorTest.schedule();
//		singleExecutorTest.scheduleAtFixedRate();
		singleExecutorTest.scheduleWithFixedDelay();
			// TODO Auto-generated catch block
		/*try {
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}

	private void execute() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(() -> System.out.println(10));
		executorService.execute(() -> System.out.println(20));
		executorService.submit(() -> {
			List<Integer> listOfIntegers = new LinkedList<>();
			listOfIntegers.add(10);
			listOfIntegers.add(20);

			return listOfIntegers;
		});
		executorService.shutdown();
	}

	private void executeWithCallable() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<List<Double>> linearList = executorService.submit(linearSumCallable);
		Future<List<Double>> oddList = executorService.submit(oddSumCallable);
		Future<List<Double>> evenList = executorService.submit(evenSumCallable);
		
		System.out.println(linearList.get());
		System.out.println(oddList);
		System.out.println(evenList);
		
		executorService.shutdown();
	}
	
	UnaryOperator<Double> linearSumOperator = t -> t + 1;
	UnaryOperator<Double> oddSumOperator = t -> t + 2;
	UnaryOperator<Double> evenSumOperator = t -> linearSumOperator.apply(t) + 1;
	
	Callable<List<Double>> linearSumCallable = () -> {
		double count = 0;
		List<Double> linearResult = new LinkedList<>();
		for (; count < 9999;) {
			count = linearSumOperator.apply(count);
			linearResult.add(count);
		}
		return linearResult;
	};

	Callable<List<Double>> oddSumCallable = () -> {
		double count = -1;
		List<Double> oddResult = new LinkedList<>();
		for (; count < 9999;) {
			count = oddSumOperator.apply(count);
			oddResult.add(count);
		}
		return oddResult;
	};

	Callable<List<Double>> evenSumCallable = () -> {
		double count = 0;
		List<Double> evenResult = new LinkedList<>();
		for (; count < 9999;) {
			count = evenSumOperator.apply(count);
			evenResult.add(count);
		}
		return evenResult;
	};
	
	private void schedule() throws InterruptedException, ExecutionException {
		Runnable task1 = () -> System.out.println("------------------------------Hello Zoo------------------------------");
		Callable<String> task2 = () -> "------------------------------Monkey------------------------------";
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		
		Future<?> result1 = service.schedule(task1, 10, TimeUnit.SECONDS);
		Future<?> result2 = service.schedule(task2, 15, TimeUnit.SECONDS);
		
		try {
			service.shutdown();	
		}
		finally {
			while(!service.isTerminated()) {
//				System.out.print("Service still not terminated.."+ " ");
//				System.out.print("result1.isDone() : " + result1.isDone() + " ");
//				System.out.print("result2.isDone() : " + result2.isDone() + " ");
			}
		}
		
		
//		System.out.println(result1.get() + " " + result2.get());
	}
	
	Runnable simpleRunnable = () -> {
		double count = 0;
		for (; count < 999999999;) {
			count = linearSumOperator.apply(count);
//			System.out.println(count);
		}
		System.err.println("Time of execution : " + LocalDateTime.now());
		System.out.println("------------------------------Hello Zoo------------------------------");
	};
	
	private void scheduleAtFixedRate() throws InterruptedException, ExecutionException {
		Runnable task1 = () ->  {
			
			System.err.println("Time of execution : " + LocalDateTime.now());
			System.out.println("------------------------------Hello Zoo------------------------------");
		};
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		
		System.err.println("Time of submission : " + LocalDateTime.now());
		service.scheduleAtFixedRate(simpleRunnable, 2, 1, TimeUnit.SECONDS);
		
		try {
			service.awaitTermination(50, TimeUnit.SECONDS);
			service.shutdown();	
		}
		finally {
			while(!service.isTerminated()) {
//				System.out.print("Service still not terminated.."+ " ");
//				System.out.print("result1.isDone() : " + result1.isDone() + " ");
//				System.out.print("result2.isDone() : " + result2.isDone() + " ");
			}
		}
		
		
//		System.out.println(result1.get() + " " + result2.get());
	}
	
	private void scheduleWithFixedDelay() throws InterruptedException, ExecutionException {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		
		System.err.println("Time of submission : " + LocalDateTime.now());
		service.scheduleWithFixedDelay(simpleRunnable, 2, 1, TimeUnit.SECONDS);
		
		try {
			service.awaitTermination(50, TimeUnit.SECONDS);
			service.shutdown();	
		}
		finally {
			while(!service.isTerminated()) {
//				System.out.print("Service still not terminated.."+ " ");
//				System.out.print("result1.isDone() : " + result1.isDone() + " ");
//				System.out.print("result2.isDone() : " + result2.isDone() + " ");
			}
		}
		
		
//		System.out.println(result1.get() + " " + result2.get());
	}
	
	private void invoke() throws InterruptedException, ExecutionException, TimeoutException {

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Supplier<Collection<Callable>> callableTaskSuppliers = () -> {
			Collection<Callable> callableSupplier = new LinkedList<>();
			callableSupplier.add(linearSumCallable);
			callableSupplier.add(oddSumCallable);
			callableSupplier.add(evenSumCallable);
			return callableSupplier;
		};

		List<Future<Double>> result = executorService
				.invokeAll((Collection<? extends Callable<Double>>) callableTaskSuppliers.get());
		
		/*Double anyResult = executorService
				.invokeAny((Collection<? extends Callable<Double>>) callableTaskSuppliers.get());
		
		System.out.println(anyResult);*/
		
		executorService.shutdown();

//		if (result.get) {
			for (Future<Double> tempResult : result) {
				//Following line doesn't wait for result
//				System.out.println(tempResult);
				
				//Following line will wait endlessly till the result is available
//				System.out.println(tempResult.get());
				
				//Following line will wait endlessly till the result is available ..with the specified time limit
				System.out.println(tempResult.get(1, TimeUnit.NANOSECONDS));
				
			}
//		}
	}
}
