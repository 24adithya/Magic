package com.pack.java;

public class ConstructorMethodTest {
	public static void main(String[] args) {
		Ac a = new Bc();
	}
}

class Ac {
	String aString = null;

	Ac() { // constructor
		this.somePolyMorphicMethod();
		aString = "class A string";
	}

	void somePolyMorphicMethod() {
		// do stuff
		System.out.println("Inside Ac");
	}
}

class Bc extends Ac {
	int posOfLetterA = 0;

	void somePolyMorphicMethod() { // overriden

		posOfLetterA = aString.indexOf("A"); // throws null pointer exception,
												// aString NOT created yet!!
	}
}
