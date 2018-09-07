package com.pack.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
	{
		
		Object test = null;
		System.out.println("test = " + (Integer)test);
		/*Accessor accessor = new Accessor();
		accessor.accessPrivateSetters();
		accessor.accessPrivateGetters();*/
	}
}

class Private
{
	private Integer number;
	private String name;
	
	public Private() {
	
	}
	
	public Private(Integer number, String name) {
		this.number = number;
		this.name = name;
	}

	private Integer getNumber()
	{
		return number;
	}

	private void setNumber(Integer number)
	{
		this.number = number;
	}

	private String getName()
	{
		return name;
	}

	private void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return getNumber() + " " + getName();
	}
}

class Accessor
{
	Private privateObj = null;
	
	public void accessPrivateSetters() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
	{
		Class privateClass = Private.class;
		privateObj = (Private) privateClass.newInstance();
		Method[] methodArray = privateClass.getDeclaredMethods();
		Method setterMethod = null;
		
		//Print all the methods
//		System.out.println();
		for(Method method : methodArray)
		{
			if(method.getName().equals("setNumber"))
			{
				setterMethod = method;
			}
			
//			System.out.print(method.getName() + " ");
		}
		setterMethod.setAccessible(true);
		setterMethod.invoke(privateObj, new Object[]{1});
		System.out.println(privateObj);
	}
	
	public void accessPrivateGetters() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Class privateClass = Private.class;
		Method[] methodArray = privateClass.getDeclaredMethods();
		Method getterMethod = null;
		
		//Print all the methods
//		System.out.println();
		for(Method method : methodArray)
		{
			if(method.getName().equals("getNumber"))
			{
				getterMethod = method;
			}
			
//			System.out.print(method.getName() + " ");
		}
		getterMethod.setAccessible(true);
		if(privateObj != null)
		{
			Object number = getterMethod.invoke(privateObj, new Object[]{});
			System.out.println(number);
		}
	}
}