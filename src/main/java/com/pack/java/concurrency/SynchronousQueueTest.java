package com.pack.java.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

	public static void main(String[] args) {

		SynchronousQueueTest test = new SynchronousQueueTest();

		BlockingQueue<String> queue = new SynchronousQueue<>(true);

		PopulateStringRunnable runnableS1 = test.new PopulateStringRunnable(queue);
		PrintStringRunnable runnableS2 = test.new PrintStringRunnable(queue);
		PrintAltStringRunnable runnableS3 = test.new PrintAltStringRunnable(queue);

		Thread s1Thread = new Thread(runnableS1);
		Thread s2Thread = new Thread(runnableS2);
		Thread s3Thread = new Thread(runnableS3);

		s3Thread.start();
		s2Thread.start();
//		s1Thread.start();
	}

	private class PopulateStringRunnable implements Runnable {

		private BlockingQueue<String> queue;

		public PopulateStringRunnable(BlockingQueue<String> queue) {
			super();
			this.queue = queue;
		}

		@Override
		public void run() {
			int count = 1;
			while (count <= 10) {
				try {
					queue.put("s" + count++);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class PrintStringRunnable implements Runnable {

		private BlockingQueue<String> queue;

		public PrintStringRunnable(BlockingQueue<String> queue) {
			super();
			this.queue = queue;
		}

		@Override
		public void run() {
			int count = 1;
			while (count <= 10) {
				try {
					queue.put("S1");
					count++;
					System.out.println(queue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class PrintAltStringRunnable implements Runnable {

		private BlockingQueue<String> queue;

		public PrintAltStringRunnable(BlockingQueue<String> queue) {
			super();
			this.queue = queue;
		}

		@Override
		public void run() {
			int count = 1;
			while (count <= 10) {
				try {
					System.out.println(queue.take());//Suspends current thread as nothing is present in queue. 
					//Control returns here as soon as data is inserted into the queue which is being done in 'PrintStringRunnable'
					//'PrintStringRunnable' - as soon as it calls <code>put</code>, it proceeds with it's <code>take</code> which is 
					// immediate statement but blocks as 'PrintAltStringRunnable' thread had already called <code>take</code> which caused
					// it to block hence, control returns here and first completes <code>take</code> and this thread continues by calling <code>put</code>
					//and gets blocked as 'PrintStringRunnable' had called <code>take</code> and hence, alternate printing of string get accomplished
					count++;
					queue.put("S2");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
