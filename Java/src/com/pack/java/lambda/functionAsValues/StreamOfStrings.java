package com.pack.java.lambda.functionAsValues;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamOfStrings {
	private static final String[] incoming = { "Crunchy Carrots", "Golden hued-bananas", "", "Bright Orange Pumpkins",
			"Little Trees of brocolli", "gains" };

	private static Function<String, String> findLastWord(String phrase) {
		Function<String, String> lastWord = (inputString) -> Arrays.asList(inputString.split(" ")).stream().reduce((allStrings, lastString) -> lastString).orElse("");
		return lastWord;
	}
	
	private static BinaryOperator<String> joinLastWords(String connector) {
		BinaryOperator<String> joinWords = (input1, input2) -> input1 + connector + input2;
		
		return joinWords;
	}
	
	private static Predicate<String> nonEmptyString(String string) {
		Predicate<String> nonEmptyString = s -> !s.isEmpty(); 
		return nonEmptyString;
	}
	
	private static Predicate<String> nonEmptyString = s -> !s.isEmpty();
//	private static BinaryOperator<String> joinLastWords = (input1, input2) -> input1 + connector + input2;
	private static Function<String, String> findLastWord = (inputString) -> Arrays.asList(inputString.split(" ")).stream().reduce((allStrings, lastString) -> lastString).orElse("");
	
	
	private static final String summarize() {
		return Arrays.asList(incoming).stream().filter(nonEmptyString).map(findLastWord).reduce(joinLastWords(" & ")).orElse("");
	}
	
	public static void main(String[] args) {
		Consumer<String> consumer = System.out::println;
		consumer.accept(summarize());
	}
}
