package com.pack.java.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildCardTest implements Comparable<WildCardTest> {

	private int number;
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		WildCardTest test = new WildCardTest();
		Box<Integer> box = new Box<>(10);
		test.displayBoxContents(box);
		
		/*List<Dog> dogs = new ArrayList<>();
		dogs.add(new Labrador());
		dogs.add(new Labrador());
		dogs.add(new Labrador());

		test.addAnimal(dogs);

		System.out.println(dogs);

		Dog[] dogsArray = new Dog[] { new Dog(), new Dog(), new Dog(), null,
				null };
		Animal[] animalArray = new Dog[] { new Dog(), new Dog(), new Dog(),
				null, null };
		test.addAnimal(dogsArray);
		System.out.println(dogsArray);

		List<Animal> animalsOnly = new ArrayList<>();
		List<Object> objectsOnly = new ArrayList<>();
		test.addAnimalsOnly(animalsOnly);

		test.extendAnimal(dogs);
		test.addAnimal(animalArray);
		
		printClassInfo();
		
		List<String> list = new ArrayList<>();
		test.testListExtn(list);*/
		
		List<Integer> li = Arrays.asList(1, 2, 3);
		List<String>  ls = Arrays.asList("one", "two", "three");
		printList(li);
		printList(ls);
		
		System.out.println("##################################");
		
		printListObj(li);
		printListObj(ls);
	}
	
	public void displayBoxContents(Box<? extends Number> boxes) {
		List<? super Number> list1 = new ArrayList<>();
		list1.add(10);
		list1.add(10.10);
		list1.add(20.20d);
		
		List<? super Integer> list = list1;
		System.out.println(list.toString());
	}
	
	public static void printListObj(List<? extends Object> list) {
	    for (Object elem : list)
	        System.out.println(elem + " ");
	    System.out.println();
	}
	
	public static void printList(List<?> list) {
	    for (Object elem : list)
	        System.out.println(elem + " ");
	    System.out.println();
	}

	private static void printClassInfo() throws ClassNotFoundException {
		Class clazz1 = Class.forName("com.pack.java.generics.WildCardTest");
		System.out.println(clazz1);
		
		WildCardTest wt = new WildCardTest();
		
		Class clazz2 = wt.getClass();
		System.out.println(clazz2);
		
		Class clazz3 = WildCardTest.class;
		System.out.println(clazz3);
	}

	private void extendAnimal(List<? extends Animal> animals) {

	}

	private void addAnimal(Animal[] animals) {
		animals[4] = new Dog();
		animals[3] = new Labrador();
		System.out.println();
	}

	private void addAnimal(List<? super Dog> animals) {
		animals.add(new Dog());
		animals.add(new Labrador());
	}

	private void addAnimalsOnly(List<? super Animal> animals) {
		animals.add(new Dog());
		animals.add(new Animal());
	}

	private void addAnimal(Dog[] dogs) {
		dogs[4] = new Dog();
		dogs[3] = new Labrador();
	}

	@Override
	public int compareTo(WildCardTest o) {
		return Integer.valueOf(number).compareTo(o.number);
	}
	
	public void testListExtn(List<String> list) {
		
	}
}

class Animal {

}

class Labrador extends Dog {

}

class Dog extends Animal {

}

class Cat extends Animal {

}

class Popcorn {
	public void pop() {
		System.out.println("popcorn");
	}
}

class Food {
	Popcorn p = new Popcorn() {
		public void sizzle() {
			System.out.println("anonymous sizzling popcorn");
		}

		public void pop() {
			System.out.println("anonymous popcorn");
		}
	};

	public void popIt() {
		final int x = 1;
		Popcorn p = new Popcorn() {
			public void sizzle() {
				System.out.println("anonymous sizzling popcorn " + x);
			}

			public void pop() {
				System.out.println("anonymous popcorn");
			}
		};
		
		p.pop(); // OK, Popcorn has a pop() method
//		p.sizzle(); // Not Legal! Popcorn does not have sizzle()
	}
}

class Box<T> {
	private T boxContent;
	
	public Box(T t) {
		this.boxContent = t;
	}

	public T getBoxContent() {
		return boxContent;
	}

	public void setBoxContent(T boxContent) {
		this.boxContent = boxContent;
	}
	
}