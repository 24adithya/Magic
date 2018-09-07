package com.pack.effective.java;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e)
	{
		ensureCapacity();
		elements[size++] = e;
		System.out.println("Pushed element is " + e);
	}

	public Object pop()
	{
		if (size == 0)
			throw new EmptyStackException();
		Object result  = elements[--size];
		elements[size] = null;
		System.out.println("Popped element is " + result);
		return result;
	}

	/**
	 * Ensure space for at least one more element, roughly doubling the capacity
	 * each time the array needs to grow.
	 */
	private void ensureCapacity()
	{
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}
	
	public static void main(String[] args)
	{
		Stack stack = new Stack();
		System.out.println(stack);
		/*Stack stackDup = stack;
		
		if(stackDup == stack && stackDup instanceof Stack)
		{
			System.out.println("lol");
		}
		
		int element = 10;
		for(int count = 0 ; count < 20 ; count++ )
		{
			stack.push(element++);
		}
		
		stack.pop();
		
		for(int count =  0; count < stack.elements.length ; count++)
		{
			System.out.println(stack.elements[count]);
		}*/
	}
	
	@Override
	public String toString()
	{
		return "Stack class pushing and popping !";
	}
}
