package com.pack.java.streams;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalTest {

	public static void main(String[] args) {
		Optional<Double> result = new OptionalTest().average();
		
		System.out.println(result.isPresent() ? result.get() : "");
	}
	
	private Optional<Double> average(int... scores) 
	{
		 if (scores.length == 0) 
			 return Optional.empty();

		 int sum = 0;
		 for (int score: scores) 
			 sum += score;
		 
		 return Optional.of((double) sum / scores.length);
	}

}
