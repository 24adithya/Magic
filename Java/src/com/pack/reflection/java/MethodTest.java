package com.pack.reflection.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodTest {

	public static void main(String[] args) {
		try {
			Class clazz = Class.forName("com.pack.reflection.java.Service");
			Service service = (Service) clazz.newInstance();
			String methodName = "testWithParams";
			Method method = clazz.getDeclaredMethod(methodName, String.class);
			method.invoke(service, new Object[] {"AAR"});
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class Service {
	public void test() {
		System.out.println("lol");
	}
	
	public void testWithParams(String name) {
		System.out.println("lel : " + name);
	}
}