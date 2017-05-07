package com.pack.java.functional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalProgTest {

	Integer id;

	public static void main(String[] args) {
		FunctionalProgTest test = new FunctionalProgTest();

		// Test Consumer
		test.consumer(test);

		// Test BiConsumer
		test.biConsumer(test);

	}

	private class People {
		private String name;

		public People(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			
			return this.name;
		}
	}

	private void biConsumer(FunctionalProgTest test) {
		Map<People, Integer> numbers = new HashMap<>();

		numbers.put(new People("Adi"), 24);
		numbers.put(new People("Abu"), 8);
		numbers.put(new People("Rup"), 14);
		numbers.put(new People("Nit"), 27);
		numbers.put(new People("Pra"), 11);
		numbers.put(new People("Moh"), 6);

		testBiFunctionLambda((k, v) -> {

			return v * v;
			/*
			 * Comparator<People> keyComparator = Comparator.comparing(p ->
			 * p.name); Map<People, Integer> sortedMap = new
			 * TreeMap<>(keyComparator);
			 * 
			 * sortedMap.forEach((t,u) -> System.out.print(t));
			 */
		}, numbers);

	}

	private void consumer(FunctionalProgTest test) {

		List<String> stringObjects = new ArrayList<>();
		stringObjects.add("Adithya");
		stringObjects.add("Abu");
		stringObjects.add("Puri");

		List<Integer> integerObjects = new ArrayList<>();
		integerObjects.add(24);
		integerObjects.add(8);
		integerObjects.add(14);
		testConsumer();
		testConsumerLambda(x -> System.out.println(x), stringObjects);
		testConsumeAndAcceptLambda(Integer::doubleValue, integerObjects);
	}

	private void testConsumer() {
		Consumer<Integer> c1 = x -> System.out.println(x);
		Consumer<Integer> c2 = System.out::println;
		Consumer<String> c3 = new MyConsumer();
		Consumer<Double> c4 = new Consumer<Double>() {

			@Override
			public void accept(Double t) {
				System.out.println(t);
			}
		};

		Consumer<String> c5 = (t) -> {
			int i = 10;
			if (i > 5) {
				System.out.println(i);
			}
		};

		c1.accept(10);
		c2.accept(20);
		c3.accept("Nitish");
		c4.accept(20.0);
		c5.accept("50");
	}

	private <T> void testConsumerLambda(Consumer<T> consumer, List<T> objects) {
		for (T t : objects) {
			consumer.accept(t);
		}
	}
	
	private <T, U> void testBiConsumerLambda(BiConsumer<T, U> consumer, Map<T, U> objects) {
		for (T t : objects.keySet()) {
			consumer.accept(t, objects.get(t));
		}
	}

	private <T, R> void testConsumeAndAcceptLambda(Function<T, R> function, List<T> objects) {
		List<R> newObjects = new ArrayList<>();
		for (T t : objects) {
			newObjects.add(function.apply(t));
		}

		testConsumerLambda(x -> System.out.println(x), newObjects);
	}

	private <T, U> void testBiFunctionLambda(BiFunction<T, U, U> biFunction, Map<T, U> mapOfObjects) {
		Set<T> keyList = (Set<T>) mapOfObjects.keySet();
		U tempValue = null;
		for (T t : keyList) {
			tempValue = mapOfObjects.get(t);
			mapOfObjects.put(t, biFunction.apply(t, tempValue));
		}
		
		testBiConsumerLambda((k, v) -> System.out.println(k + " " + v), mapOfObjects);
	}
}

class MyConsumer implements Consumer<String> {

	@Override
	public void accept(String t) {
		System.out.println(t);
	}

}
