package com.pack.java;

public class InstanceOfTest {

	public static void main(String[] args) {

		SimplePipeServiceRequest<Integer> serviceRequest = new SimplePipeServiceRequest<Integer>();
		Object[] params = new Object[] {serviceRequest};
		new InstanceOfTest().execute(params);
	}

	private void execute(Object[] args) {
		System.out.println(args[0] instanceof ServiceRequest);
		System.out.println(args[0] instanceof SimpleServiceRequest);
		System.out.println(args[0] instanceof SimplePipeServiceRequest);
	}
}

interface ServiceRequest<T> {
	
}

class SimpleServiceRequest<T> implements ServiceRequest<T> {
	
}

class SimplePipeServiceRequest<T> extends SimpleServiceRequest<T> {
	
}