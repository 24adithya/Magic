package com.pack.java.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaTest {

	boolean wantWhetherCanHop = true;

	class Animal {
		private String species;
		private boolean canHop;
		private boolean canSwim;

		public Animal(String speciesName, boolean hopper, boolean swimmer) {
			species = speciesName;
			canHop = hopper;
			canSwim = swimmer;
		}

		public boolean canHop() {
			return canHop;
		}

		public boolean canSwim() {
			return canSwim;
		}

		public String toString() {
			return species;
		}
	}

	public interface CheckTrait {
		boolean test(Animal a);
	}

	public class CheckIfHopper implements CheckTrait {
		public boolean test(Animal a) {
			return a.canHop();
		}
	}

	public static void main(String[] args) {

		LambdaTest test = new LambdaTest();

		List<Animal> animals = new ArrayList<Animal>(); // list of animals
		animals.add(test.new Animal("fish", false, true));
		animals.add(test.new Animal("kangaroo", true, false));
		animals.add(test.new Animal("rabbit", true, false));
		animals.add(test.new Animal("turtle", false, true));

		// print(animals, test.new CheckIfHopper()); // pass class that does
		// check
		// print(animals, (Animal a) -> a.canHop() == test.wantWhetherCanHop );
		// // pass class that does check

		printWithPredicates(animals, a -> a.canHop());
		printWithPredicates(animals, a -> a.canSwim());

		check((h, l) -> {return h.toString().length() > l.toString().length(); }, new StringBuilder("abcd"), new StringBuilder("def"));

	}

	private static void print(List<Animal> animals, CheckTrait checker) {
		for (Animal animal : animals) {
			if (checker.test(animal)) // the general check
				System.out.print(animal + " ");
		}
		System.out.println();
	}

	private static void printWithPredicates(List<Animal> animals, Predicate<Animal> checker) {
		for (Animal animal : animals) {
			if (checker.test(animal))
				System.out.print(animal + " ");
		}
		System.out.println();
	}

	private static void testString(String a) {

	}

	interface Climb {
		boolean isTooHigh(StringBuilder height, StringBuilder limit);
	}

	static void check(Climb climb, StringBuilder height, StringBuilder limit) {
		if (climb.isTooHigh(height, limit))
			System.out.println("too high");
		else
			System.out.println("ok");
	}

}
