package com.pack.java.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueueTest {

	public static void main(String[] args) {
		LinkedBlockingQueueTest blockingQueueTest = new LinkedBlockingQueueTest();
		blockingQueueTest.testLinkedBlockingQueueTest();
	}

	private void testLinkedBlockingQueueTest() {
		try {
			BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
			blockingQueue.offer(39);
			blockingQueue.offer(3, 4, TimeUnit.SECONDS);
			System.out.println(blockingQueue.poll());
			System.out.println(blockingQueue.poll());
			System.out.println(blockingQueue.poll(10, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
			// Handle interruption
		}
	}
}
