package com.pack.java;

public class AbstractionTest 
{
	public static void main(String[] args)
	{
		A a = new E();
		a.print();
		a = new D();
		a.print();
	}
}

interface A
{
	void print();
}

abstract class B implements A
{ 
//	T t;
	
	/*public B(T t) {
		this.t = t;
	}*/
	
	
	
	public void print()
	{
		System.out.println("B");
		printS();
	}
	
	abstract void printS();
}
class valueHolder<T>
 {
	T t;
	
	public <T> T getInstance(T t) {
		return null;
	}
 }
class E extends B {

	/*public E(T t) {
		super(t);
	}*/
	
	@Override
	void printS()
	{
		System.out.println("E");
	}
}

class D extends B {

	/*public D(T t) {
		super(t);
	}*/
	
	@Override
	void printS()
	{
		System.out.println("D");
	}
}

