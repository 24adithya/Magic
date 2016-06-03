package com.pack.java.design.patterns;

public class SingletonTest {

	public static void main(String[] args)
	{
		Thread[] threadArray = new Thread[1000];
		SingletonRunnable runnable = new SingletonRunnable();
		for(int count = 0; count < threadArray.length ; count++)
		{
			threadArray[count] = new Thread(runnable);
			threadArray[count].start();
		}
	}
}

class SingletonRunnable implements Runnable
{
	@Override
	public void run()
	{
		for(int count = 0; count < 10 ; count ++)
		{
			SingleTon.getInstance();
		}
	}
}

class SingleTon
{
	private static int count = 0;
	//private static SingleTon INSTANCE;  
	
	private SingleTon()
	{
		
	}
	
	private static final class SingletonHolder 
	{
		private static SingleTon INSTANCE = new SingleTon();
		private SingletonHolder()
		{
			System.out.println("count = " + ++count);
		}
	}
	
	public static SingleTon getInstance()
	{
		return SingletonHolder.INSTANCE;
		
		/*if(INSTANCE == null)
		{
			INSTANCE = new SingleTon();
			System.out.println("count = " + ++count);
		}
		return INSTANCE;*/
	}
}