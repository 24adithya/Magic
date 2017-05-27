package com.pack.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager {
	private int sheepCount = 0;
	private AtomicInteger atomiSheepCount = new  AtomicInteger(0);

	private void incrementAndReport() {
		System.out.print((++sheepCount) + " ");
	}
	
	private synchronized void  incrementAndReportAtomically() {
		System.out.print(atomiSheepCount.incrementAndGet() + " ");
	}

	public static void main(String[] args) {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);
			SheepManager manager = new SheepManager();
//			manager.reportNormally(service);
			manager.reportAtomically(service);
		} finally {
			if (service != null)
				service.shutdown();
		}
	}
	
	private void reportNormally(ExecutorService service) {
		for (int i = 0; i < 10; i++)
			service.submit(() -> incrementAndReport());
	}
	
	private void reportAtomically(ExecutorService service) {
		for (int i = 0; i < 10; i++)
			service.submit(() -> incrementAndReportAtomically());
	}
}
