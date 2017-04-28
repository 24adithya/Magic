package com.pack.java.compare;

import java.util.Comparator;

public class SquirrelCompare {
	private int weight;
	private String species;

	public SquirrelCompare(String theSpecies) {
		if (theSpecies == null)
			throw new IllegalArgumentException();
		species = theSpecies;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getSpecies() {
		return species;
	}
}

class ChainingComparator implements Comparator<SquirrelCompare> {
	public int compare(SquirrelCompare s1, SquirrelCompare s2) {
		Comparator<SquirrelCompare> c = Comparator.comparing(s -> s.getSpecies());
		c = c.thenComparingInt(s -> s.getWeight());
		return c.compare(s1, s2);
	}
}