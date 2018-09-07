package com.pack.java.patterns.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverPatternDemo {

	public static void main(String[] args)
	{
		Client adams = new Client("Adams");
		Client edge = new Client("Edge");
		
		StreamTest test = new StreamTest();
		test.addObserver(edge);
		test.addObserver(adams);
		
		test.tweet();
	}
}

class StreamTest extends Observable
{
	public void tweet()
	{
		setChanged();
		notifyObservers();
	}
}

class Client implements Observer
{
	private String name;
	
	public Client(String name) {
		this.name = name;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		System.out.println(getName() + " has tweeted.");
	}

	public String getName()
	{
		return name;
	}

	/*public void setName(String name)
	{
		this.name = name;
	}*/
}
