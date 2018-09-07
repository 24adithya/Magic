package com.pack.effective.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDanger {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		Class elvis = Class.forName("com.pack.effective.java.Elvis");
		Method instanceMethod = elvis.getMethod("getInstance", null);
		instanceMethod.invoke(null, null);
		
		//Accessing private constructor
		Constructor<Elvis>[] constructors;
		constructors = (Constructor<Elvis>[]) Elvis.class.getDeclaredConstructors();
		constructors[0].setAccessible(true);
		Elvis elvisObj = constructors[0].newInstance(null);
		System.out.println(elvisObj);
//		Elvis elvisObj = (Elvis) elvis.newInstance();
//		System.out.println(elvisObj);
	}
}

// Singleton with static factory
class Elvis {
	private static final Elvis INSTANCE = new Elvis();

	private Elvis() {  }

	public static Elvis getInstance()
	{
		System.out.println("Inside 'Elvis' constructor");
		return INSTANCE;
	}

	public void leaveTheBuilding() {  }
}