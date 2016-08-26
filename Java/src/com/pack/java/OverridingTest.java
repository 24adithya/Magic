package com.pack.java;

public class OverridingTest {

	public static void main(String[] args) {

	}
}

class ParamParent {
	
}

class ParamChild extends ParamParent {
	
}

class Parent1 {
	
	public ParamParent service(ParamParent paramParent) {
		return null;
	}
}

class Child1 extends Parent1 {
	
	@Override
	public ParamChild service(ParamParent param) {
		return null;
	}
}
