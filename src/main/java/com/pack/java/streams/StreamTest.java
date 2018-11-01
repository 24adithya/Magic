package com.pack.java.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
	   ReportQueryResult res1 = new ReportQueryResult();
	   ReportQueryResult res2 = new ReportQueryResult();
	   ReportQueryResult res3 = new ReportQueryResult();
	   
	   List<ReportQueryResult> list  = new ArrayList<>();
	   list.add(res1);
	   list.add(res2);
	   list.add(res3);
	   List<Double> opsTrxIdList =  new ArrayList<>();
	   list.stream().forEach(result -> opsTrxIdList.add((Double)result.map.get("opsTrxId")));
	   
	   opsTrxIdList.stream().forEach(System.out::println);
	}
	/*{
		Stream.iterate(1, n -> n + 1).limit(10).parallel().unordered()
				// .peek((value) -> System.out.println("peeked value = " + value))
				.forEach((i) -> {
					System.out.println("printed value = " + i);
				});

		OptionalDouble r = IntStream.of(10, 20).average();
		if (r.isPresent()) {
			r.getAsDouble();
		} else {
			r.orElseThrow(() -> {
				throw new IllegalStateException("Nahi aaya value!");
			});
		}

//		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		Stream<String> str1 = Stream.of("a", "b", "c");
		Stream<String> str2 = Stream.of("d", "e", "f");

		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		
		String[] ohMyArray = new String[] {"lions", "tigers", "bears"};
		Map<String, Integer> mapS = new HashMap<>();
		Map<Integer, String> mapI = new HashMap<>();
		for(String str : ohMyArray) {
			mapS.put(str, str.length());
			mapI.put(str.length(), str);
		}
		System.out.println(mapS); // // {5=lions,bears, 6=tigers}
		System.out.println(mapS.getClass()); // class. java.util.TreeMap
		
		System.out.println(mapI); // // {5=lions,bears, 6=tigers}
		System.out.println(mapI.getClass()); // class. java.util.TreeMap
		
		Map<Integer, String> map = ohMy
				.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
		System.out.println(map); // // {5=lions,bears, 6=tigers}
		System.out.println(map.getClass()); // class. java.util.TreeMap

		// ohMy.flatMap(n -> n.str)

		Optional<Integer> optionalInteger = Optional.of(120);
		Stream.of(optionalInteger.map(n -> "" + n).filter(n -> n.length() == 3).orElseGet(() -> "Nothing"))
				.forEach(System.out::println);

		// integers.unordered().limit(10).forEach((i) -> {System.out.print(i + " ") ;});
		System.out.println("\n\n");
		// Map<Integer,Boolean> result =
		// integers.limit(1000).unordered().collect(Collectors.toConcurrentMap(k -> k, v
		// -> Boolean.FALSE ));
		// System.out.println(result.keySet());
		// System.out.println(result.keySet().size());
		
		
		 * Predicate<? super String> predicate = s -> s.startsWith("g"); Stream<String>
		 * stream1 = Stream.generate(() -> "growl! "); Stream<String> stream2 =
		 * Stream.generate(() -> "growl! "); boolean b1 = stream1.anyMatch(predicate);
		 * boolean b2 = stream2.allMatch(predicate); System.out.println(b1 + " " + b2);
		 * 
		 * Comparator<Integer> c = (a,b) -> b-a; Optional<Integer> optional =
		 * Stream.iterate(1, n -> n += 1).limit(5).max(c);
		 * System.out.println(optional.get());
		 
		StreamTest streamTest = new StreamTest();
		streamTest.testAssociateParallelStream();
//		streamTest.testSimpleStreamCreation();

//		streamTest.testCombinedData();
//		streamTest.testSumOddNos();
		streamTest.testMulAndSumOddNos();
//		streamTest.testPeek();
//		streamTest.testLimit();
//		streamTest.testReduce();
//		streamTest.testMutableReduce();
	}*/

	private void testSumOddNos() {
		System.out.println("Sum is : " + Stream.iterate(1, n -> n + 1).filter(x -> x % 2 == 1).limit(5)
				.peek(x -> System.out.print(x + " ")).mapToInt(x -> x).sum());
	}
	
	private void testMulAndSumOddNos() {
		System.out.println("Sum is : " + Stream.iterate(1, n -> n + 1).filter(x -> x % 2 == 1).limit(5)
				.peek(x -> System.out.print(x + " ")).mapToInt(x -> x).mapToObj(x -> x * x)
				.peek(x -> System.out.print(x + " ")).mapToInt(x -> x).sum());
		
		LongStream longs = LongStream.of(5, 10);
		long sum = longs.peek(System.out::print).sum();
		
		System.out.println();
		
		longs = LongStream.range(5, 10);
		sum = longs.peek(System.out::print).sum();
		
		System.out.println();
		
		longs = LongStream.rangeClosed(5, 10);
		sum = longs.peek(System.out::print).sum();
	}
	
	private void testMapContents() {
		Map<String, Boolean> employeeAttrs = new HashMap<>();
		employeeAttrs.put("Adithya", true);
		employeeAttrs.put("Mohit", false);
		employeeAttrs.put("Pranali", true);
		employeeAttrs.put("Gorakh", true);
		employeeAttrs.put("Sravya", false);
		employeeAttrs.put("Vidyasagar", false);
		
//		employeeAttrs.values().stream().filter(t -> true == t).TODO
	}
	
	private void testCombinedData() {
		
		Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
		infinite.filter(x -> x % 2 == 1).peek(System.out::print).limit(5).forEach(System.out::print);
		//1133557799 - since, 'filter' is above 'peek', only odd numbers are printed.

		System.out.println();
		
		infinite = Stream.iterate(1, x -> x + 1);
		infinite.peek(System.out::print).filter(x -> x % 2 == 1).limit(5).forEach(System.out::print);
		//11233455677899 - since, 'peek' is above 'filter', even numbers are printed.
		
		System.out.println();
		
		infinite = Stream.iterate(1, x -> x + 1);
		infinite.limit(5).peek(System.out::print).filter(x -> x % 2 == 1).forEach(System.out::print);
		// 11233455 - we are limiting numbers first ('limit'), so only first 5 numbers
		// are considered for further operation. Additionally,
		
		System.out.println();
//		Stream.generate(() -> "Olaf Lazisson")
//		.filter(n -> n.length() == 4)
//		.limit(2)
//		.sorted()
//		.forEach(System.out::println);
	}
	
	private void testPeek() {
		List<Integer> numbers = new ArrayList<>();
		List<Character> letters = new ArrayList<>();
		numbers.add(1);
		letters.add('a');
		Stream<List<?>> stream = Stream.of(numbers, letters);
		stream.map(List::size).forEach(System.out::print); // 11
	}
	
	private void testLimit() {
		Stream<String> testStream = Stream.generate(() -> "Adams");
		testStream.forEach(System.out::println);
	}
	
	private void testAssociateParallelStream() {
		Stream<Character> charStream =  Stream.of('i',' ', 'a', 'm',' ', 'g', 'r', 'o', 'o', 't');
		String result = charStream.parallel().reduce("", (c,s) -> (c + s), (s1 , s2) -> (s1 + s2));
		System.out.println(result);
	}
	
	private void testConcurrentMapCollectors() {
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
		ConcurrentMap<Integer, String> map = ohMy
				.collect(Collectors.toConcurrentMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
		System.out.println(map); // {5=lions,bears, 6=tigers}
		System.out.println(map.getClass()); // java.util.concurrent.ConcurrentHashMap
	}
	
	private void testMutableReduce() {
		Stream<String> stream = Stream.of("w", "o", "l", "f");
		StringBuilder word = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);

		System.out.println(word);
	}

	private void testReduce() {
		Stream<Integer> intStream = Stream.of(1, 2, 3);
		Optional<Integer> result = intStream.reduce((a, b) -> a * b);
		System.out.println(result.isPresent() ? result.get() : result.orElse(null));
	}

	private void testSimpleStreamCreation() {
		Stream<Double> randoms = Stream.generate(Math::random);
		Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

		// randoms.forEach(System.out::println);
		oddNumbers.forEach(System.out::println);
	}
}


class ReportQueryResult {
    Map<String, Object> map;
    
    public ReportQueryResult() {
        map = new HashMap<>();
        map.put("opsTrxId", Math.random() * 50);
        map.put("opsTrxName", "Name " + Math.random() * 50);
        map.put("opsTrxPrice", "Price " + Math.random() * 50);
    }
}