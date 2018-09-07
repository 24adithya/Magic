package com.pack.java.thread;

public class SynchronizationTest {

	private static int i = 0;
	
	public static void main(String[] args)
	{
		SynchronizationTest test = new SynchronizationTest();
		test.syncTest();
	}
	
	private void syncTest()
	{
		Thread[] threadArray = new Thread[10];
		
		int count = 0;
		
		for(Thread thread : threadArray)
		{
			if(count <= 4)
			{
				thread = new Thread(myRunnable1);
			}
			else
			{
				thread = new Thread(myRunnable2);
			}
			thread.start();
			count++;
		}
	}

	Runnable myRunnable1 = new Runnable() {
		
		@Override
		public void run()
		{
			try
			{
				method1();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	Runnable myRunnable2 = new Runnable() {
		
		@Override
		public void run()
		{
			method2();
		}
	};
	
	private static void method1() throws InterruptedException
	{
//		synchronized(SynchronizationTest.class)
		{
		Thread.sleep(7200l);i++;
		System.out.println("Inside method1 "+i);
		}
	}
	
	private static void method2()
	{
		i += 2;
		System.out.println("Inside method2 " + i);
	}
}
