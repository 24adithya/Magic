package com.pack.java.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RabbitsSort {
	static class Rabbit {
		int id;

		public static int byWeight(Rabbit r1, Rabbit r2) {
			return r1.id - r2.id;
		}
	}

	public static void main(String[] args) {

	}

	private void sortRabbit() {
		List<Rabbit> rabbits = new ArrayList<>();
		rabbits.add(new Rabbit());
		Comparator<Rabbit> c = Rabbit::byWeight;// (r1, r2) -> r1.id - r2.id;
		Collections.sort(rabbits, c);
		Collections.sort(rabbits, (Rabbit r1, Rabbit r2) -> {
			return r1.id - r2.id;
		});
		Collections.sort(rabbits, Rabbit::byWeight);

		Set<Rabbit> rabbitSet = new TreeSet<>((r1, r2) -> r1.id - r2.id);
		rabbitSet.add(new Rabbit());

		System.out.println(rabbitSet);
	}
}