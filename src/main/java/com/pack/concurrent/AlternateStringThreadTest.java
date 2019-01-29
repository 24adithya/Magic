package com.pack.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlternateStringThreadTest {

	public static void main(String[] args) {

		AlternateStringThreadTest test = new AlternateStringThreadTest();

		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();

		PrintStringRunnable runnableS1 = test.new PrintStringRunnable(lock, condition, "S1");
		PrintAltStringRunnable runnableS2 = test.new PrintAltStringRunnable(lock, condition, "S2");

		Thread s1Thread = new Thread(runnableS1);
		Thread s2Thread = new Thread(runnableS2);

		s2Thread.start();
		s1Thread.start();
	}

	private class PrintStringRunnable implements Runnable {

		private Lock lock;
		private Condition condition;
		private String stringToPrint;

		public PrintStringRunnable(Lock lock, Condition condition, String stringToPrint) {
			super();
			this.lock = lock;
			this.condition = condition;
			this.stringToPrint = stringToPrint;
		}

		@Override
		public void run() {
			int count = 1;
			while (count <= 10) {
				if (lock.tryLock()) {
					try {
						System.out.println(stringToPrint);
						count++;
						condition.signal();
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						lock.unlock();
					}
				}
			}
		}
	}

	private class PrintAltStringRunnable implements Runnable {

		private Lock lock;
		private Condition condition;
		private String stringToPrint;

		public PrintAltStringRunnable(Lock lock, Condition condition, String stringToPrint) {
			super();
			this.lock = lock;
			this.condition = condition;
			this.stringToPrint = stringToPrint;
		}

		@Override
		public void run() {
			int count = 1;
			while (count <= 10) {
				if (lock.tryLock()) {
					try {
						condition.await();
						System.out.println(stringToPrint);
						count++;
						condition.signal();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						lock.unlock();
					}
				}
			}
		}
	}
}
