package com.pack.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolExecutorTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
	}
}
