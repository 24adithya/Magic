package com.pack.java.patterns.observer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class CustomObserverPatternDemo {

	public static void main(String[] args)
	{
		Subject twitter = new Twitter();
		
		Phone phone = new Phone(twitter);
		phone.addMessage("I am groot !");
		
		Tablet tablet = new Tablet(twitter);
		tablet.addMessage("I am wolverine !");
	}
}

abstract class Observer
{
	protected Subject subject;
	abstract public void update();
}

abstract class Subject
{
	private Set<Observer> observers;
	
	public void attach(Observer observer)
	{
		observers.add(observer);
	}
	
	public Subject()
	{
		observers = new HashSet<>();
	}
	
	abstract public void putMessage(String message);
	abstract public String getMessage();
	
	public void notifyObservers()
	{
		for(Observer observer : observers)
		{
			observer.update();
		}
	}
}

class Twitter extends Subject
{
	private Deque<String> deque;

	public Twitter() {
		deque = new ArrayDeque<>();
	}
	
	@Override
	public void putMessage(String message)
	{
		deque.add(message);
		notifyObservers();
	}

	@Override
	public String getMessage()
	{
		return deque.getLast();
	}
}

class Phone extends Observer
{
	public Phone(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	public void addMessage(String message)
	{
		subject.putMessage(message + "sent from Phone");
	}
	
	@Override
	public void update()
	{
		System.out.println("Phone - " + subject.getMessage());
	}
}

class Tablet extends Observer
{
	public Tablet(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	public void addMessage(String message)
	{
		subject.putMessage(message + "sent from Tablet");
	}
	
	@Override
	public void update()
	{
		System.out.println("Tablet - " + subject.getMessage());
	}
}
