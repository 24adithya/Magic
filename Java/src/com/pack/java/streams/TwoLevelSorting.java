package com.pack.java.streams;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoLevelSorting {

	private final class Charge {
		int chargeId;
		String relatedCharge;
		String charge;
		String status;
		
		public Charge(int chargeId, String status) {
			super();
			this.chargeId = chargeId;
			this.status = status;
		}
		
		public Charge(String charge, String status) {
			super();
			this.charge = charge;
			this.status = status;
		}
		
		public Charge(String charge, String relatedCharge, String status) {
			super();
			this.charge = charge;
			this.relatedCharge = relatedCharge;
			this.status = status;
		}
		
		@Override
		public String toString() {
			
			return charge + " " + relatedCharge + " " + status;
		}
	}
	
	public static void main(String[] args) {
		new TwoLevelSorting().testTwoWaySort();
		new TwoLevelSorting().testMapValuesContents();
	}
	
	private void testTwoWaySort() {
//		Charge c1 = new Charge(10, "Original");
//		Charge c2 = new Charge(10, "Reversal");
//		Charge c3 = new Charge(10, "Revised");
//		
//		Charge c4 = new Charge(20, "Original");
//		Charge c5 = new Charge(20, "Reversal");
//		Charge c6 = new Charge(20, "Revised");
//		
//		Charge c8 = new Charge(15, "Reversal");
//		Charge c7 = new Charge(15, "Original");
//		Charge c9 = new Charge(15, "Revised");
		
		Charge c7 = new Charge("30", null, "Original");
		Charge c8 = new Charge("40", "30", "Reversal");
		Charge c9 = new Charge("42", "30", "Revised");
		
		Charge c2 = new Charge("11", "10", "Reversal");
		Charge c1 = new Charge("10", null, "Original");
		Charge c3 = new Charge("12", "10", "Revised");
		
		Charge c5 = new Charge("22", "21", "Reversal");
		Charge c6 = new Charge("23", "21", "Revised");
		Charge c4 = new Charge("21", null, "Original");
		
		List<Charge> list = new LinkedList<>();
		list.add(c1);
		list.add(c4);
		list.add(c5);
		list.add(c2);
		list.add(c6);
		list.add(c3);
		list.add(c9);
		list.add(c8);
		list.add(c7);
		
	    Comparator<Charge> comparator = Comparator.comparing(charge -> Integer.parseInt( charge.charge) );
//	    comparator = comparator.thenComparing(Comparator.comparing(charge -> Integer.parseInt( charge.relatedCharge) ));
	    comparator = comparator.thenComparing(Comparator.comparing(charge -> charge.status ));
		
	    list.stream().sorted(comparator).collect(Collectors.toList()).forEach(System.out::println);
	    			 
	    			 
	    
//	    list.stream().sorted(comparator.reversed()).forEach(System.out::println);
	    
	    
	}
	
	private void testMapValuesContents() {
		Map<String, String[]> statusAttrs = new HashMap<>();
		statusAttrs.put("Initial", new String[] { "Approved", "Adams3", "Finalized" });
		statusAttrs.put("Adams1", new String[] { "Adams2", "Adams3", "Finalized" });
		statusAttrs.put("Adams2", new String[] { "Adams3", "Finalized" });

		statusAttrs.values().stream()
							.map(t -> Stream.of(t))
							.flatMap(array -> array.sequential())
							.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
							.entrySet().parallelStream().peek(System.out::println).filter(t -> !t.getKey().equals("Finalized")).filter( t -> t.getValue() == statusAttrs.size()).map(entry -> entry.getKey()).forEach(System.out::println);;
	}
}
