package com.pack.java.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
//import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
//import java.util.function.UnaryOperator;

public class Java8Test {

	public static void main(String[] args) {
		Java8Test test = new Java8Test();
		String[] strArray = new String[]{"Nitish"};
		strArray[0].replace('N', 'A');
		System.out.println(strArray[0]);
		String name = "Nitish";
		name.replace('N', 'A');
		System.out.println(name);
//		test.testRemoveIf();
//		test.testReplaceAll();
		test.testMapAdditions();
	}

	private void testRemoveIf() {
		
		List<String> list = new ArrayList<>();
		list.add("Magician");
		list.add("Assistant");
		System.out.println(list); // [Magician, Assistant]
		String s1 = "M";
		list.removeIf(s -> s.startsWith("A"));
		list.removeIf(s1 :: startsWith);
		list.removeIf(String :: isEmpty);
//		list.removeIf((a) -> true);
		System.out.println(list); // [Magician]
	}
	
	private void testReplaceAll() {
//		Queue<Integer> queue = new ArrayDeque<>();
//		queue.add(10);
//		queue.add(20);
//		queue.add(30);
//		queue.replaceAll(e -> {return e*2;});
		List<Integer> list = new LinkedList<>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.replaceAll(e -> e*2);
		System.out.println(list);
		
		list.forEach(l -> System.out.println(l));
	}

	private void lambdas() {

		Consumer<List<Integer>> methodRef1 = Collections::sort;
		Consumer<List<Integer>> lambda1 = l -> Collections.sort(l);
		Consumer<List<Integer>> lambda11 = l -> System.out.println(l);
		Consumer<List<Integer>> lambda111 = System.out::println;

		String str = "abc";
		Predicate<String> methodRef2 = str::startsWith;
		Predicate<String> lambda2 = s -> str.startsWith(s);

		Predicate<String> methodRef3 = String::isEmpty;
		Predicate<String> lambda3 = s -> s.isEmpty();

		Supplier<ArrayList> methodRef4 = ArrayList::new;
		Supplier<ArrayList> lambda4 = () -> new ArrayList();
	}
	
	private void testMapAdditions() {
		Map<String, Integer> counts = new HashMap<>();
		Map<String, String> mergeMap = new HashMap<>();
		
		
		//Merge
		mergeMap.put("Adithya", "Hummus");
		mergeMap.put("Mohit", "Chaap");
		mergeMap.put("Nitish", "Protein");
		BiFunction<String, String, String> mergeMapper = (v1, v2) -> {
			int sum1 = 0, sum2 = 0;
			if(v1.length() > 0 && v2.length() > 0) {
				
				for(int i=0, j=0; i < v1.length() || j < v2.length() ; i++, j++) {
					if(i < v1.length()) 
						sum1 += v1.charAt(i);
					
					if(j < v2.length())
						sum2 += v2.charAt(j);
				}
			}
			System.out.println("sum for " + v1 + " is : " + sum1);
			System.out.println("sum for " + v2 + " is : " + sum2);
			if(sum1 <= sum2) {
				return v2;
			}
			else {
				return v1;
			}
			//TODO: Can this be done using Comparator.comparing ?
		};
				
		mergeMap.merge("Adithya", "Protein", mergeMapper);
		mergeMap.merge("Nitish", "Peanuts", mergeMapper);
		mergeMap.merge("Mohit", "Extravaganza", mergeMapper);
		
		System.out.println(mergeMap);
	
		//Compute if present
		BiFunction<String, Integer, Integer> presentMapper = (k,v) -> v + 1;
		
		counts.clear();
		counts.put("AAR", 1);
		counts.put("Adams", 24);
		counts.computeIfPresent("AAR", presentMapper);
		counts.computeIfPresent("Edge", presentMapper);
		counts.computeIfPresent("Adams", presentMapper);
		
		System.out.println("Map contents after computeIfPresent : " + counts);
		counts.forEach((k,v) -> System.out.println(k));
	
		java.util.function.Function<String, Integer> absentMapper = k -> 1;		
		
		//Compute if absent
		counts.clear();
		counts.put("AAR", 1);
		counts.put("Adams", 24);
		
		counts.computeIfAbsent("AAR", absentMapper);
		counts.computeIfAbsent("Edge", absentMapper);
		counts.computeIfAbsent("Adams", absentMapper);
		
		System.out.println("Map contents after computeIfAbsent : " + counts);
		counts.forEach((k,v) -> System.out.println(v));
	}

}

class Gen<T,S> {
	
	<T> UnaryOperator<T> identity() {
        return t -> t;
    }
}

@FunctionalInterface
interface UnaryOperator<T> extends Function<T, T> {

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @param <T> the type of the input and output of the operator
     * @return a unary operator that always returns its input argument
     */
    static <T> UnaryOperator<T> identity() {
        return t -> t;
    }
}


@FunctionalInterface
interface Function<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}