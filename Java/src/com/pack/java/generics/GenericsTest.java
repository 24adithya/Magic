package com.pack.java.generics;

public class GenericsTest {
	public static void main(String[] args) {
		System.out.println( Util.compare(new Pair<Integer, String>(10,"Adams"), new Pair<Integer, String>(20,"Adams")) );
	}
}

class Util {
	public static <K,V> boolean compare(Pair<K,V> pair1, Pair<K,V> pair2) {
		if(pair1.key.equals(pair2.key) && pair1.value.equals(pair2.value)) {
			return true;
		} else {
			return false;
		}
	}
}

class E extends A {}
class A { /* ... */ }
interface B { /* ... */ }
interface C { /* ... */ }

//class D <T extends A & B & C> { /* ... */ }

class D <T extends E & B & C> { /* ... */ }  // compile-time error

class Pair<K,V> {
	K key;
	V value;
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public void compare(K key, V value) {
		
	}
	
	public <T,U,V> boolean compareThree(T value1, U value2, V value3) {
		if(value1.equals(value2) && value2.equals(value3)) {
			return true;
		} else {
			return false;
		}
	}
}