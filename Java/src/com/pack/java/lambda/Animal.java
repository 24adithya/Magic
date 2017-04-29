package com.pack.java.lambda;

public class Animal {
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

interface CheckTrait {
	public boolean test(Animal a);
}

class FindMatchingAnimals {
	private static void print(Animal animal, CheckTrait trait) {
		if (trait.test(animal))
			System.out.println(animal);
	}

	public static void main(String[] args) {
		print(new Animal("fish", false, true), a -> a.canHop());
		print(new Animal("kangaroo", true, false), a -> {
			return a.canHop();
		});
	}
}

class Flippers {
	public void flap() {
		System.out.println("The flippers flap back and forth");
	}
}

class WebbedFeet {
	public void kick() {
		System.out.println("The webbed feet kick to and fro");
	}
}

class Penguin {
	private final Flippers flippers;
	private final WebbedFeet webbedFeet;

	public Penguin() {
//		this.flippers = new Flippers();
//		this.webbedFeet = new WebbedFeet();
	}
	
	{
		this.flippers = new Flippers();
		this.webbedFeet = new WebbedFeet();
	}

	public void flap() {
		this.flippers.flap();
	}

	public void kick() {
		this.webbedFeet.kick();
	}
}