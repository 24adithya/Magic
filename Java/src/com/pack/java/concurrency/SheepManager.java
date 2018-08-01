package com.pack.java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager {
	private volatile int sheepCount = 0;
	private AtomicInteger atomiSheepCount = new  AtomicInteger(0);

	private void incrementAndReport() {
		for(int i = 0 ; i < 100 ; i++) {}
		System.out.print((++sheepCount) + " ");
	}
	
	private int incrementAndReportCallable() {
		/*try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		for(int i = 0 ; i < 100 ; i++) {}
		synchronized (atomiSheepCount) {
			System.out.print((++sheepCount) + " ");
		}
		return sheepCount;
	}
	
	private synchronized void  incrementAndReportAtomically() {
		System.out.print(atomiSheepCount.incrementAndGet() + " ");
	}

	public static void main(String[] args) {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);
			SheepManager manager = new SheepManager();
			manager.reportNormally(service);
//			manager.reportAtomically(service);
		} finally {
			if (service != null)
				service.shutdown();
		}
	}
	
	private void reportNormally(ExecutorService service) {
		List<Callable<Integer>> callables = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			callables.add(() -> incrementAndReportCallable());
		}
		try {
			service.invokeAll(callables);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void reportAtomically(ExecutorService service) {
		for (int i = 0; i < 10; i++) {
			service.submit(() -> incrementAndReportAtomically());
		}
	}
}
