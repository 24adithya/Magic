package com.pack.java;

public class BoxingTest {

	public static void main(String[] args) {
		BoxingTest test = new BoxingTest();
		boolean answer = test.findBoolean() != null ? test.findBoolean() : false;
		System.out.println(answer);
	}

	
	private Boolean findBoolean() {
		return null;
	}
}