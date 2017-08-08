package com.pack.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class CollectionsToArray {

	public static void main(String[] args) {
		CollectionsToArray collectionsToArray = new CollectionsToArray();

		// Set<Long> longNumbers = collectionsToArray.prepareNumbersSet(); 
		// Set<Long> longNumbers = collectionsToArray.prepareNumbersSetFromMap();
		// Set<Long> longNumbers = collectionsToArray.prepareNumbersSetFromMapOfMap();
		// collectionsToArray.convertKeySetToArray(longNumbers);

		// Following code is for recreating java bug
		Map<String, Map<String, Object>> mapOfNumbers = collectionsToArray.prepareStrangeNumbersSetFromMapOfMap();

		Object obj = mapOfNumbers;
		collectionsToArray.convertMapToKeySetToArray(obj);

		Set<String> stringNumbersMap = collectionsToArray.prepareStringNumbersSetFromMapOfMap();
		collectionsToArray.convertStringKeySetToArray(stringNumbersMap);
	}

	/*
	  public static void main(String[] args) {
	  
	  CollectionsToArray collectionsToArray = new CollectionsToArray();
	  
	  Map<String, Map<String, Object>> mapOfNumbers =
	  collectionsToArray.prepareStrangeNumbersSetFromMapOfMap();
	  
	  Object obj = mapOfNumbers;
	  collectionsToArray.convertMapToKeySetToArray(obj);
	  
	  }
	 */

	private Map<String, Map<String, Object>> prepareStrangeNumbersSetFromMapOfMap() {
		Map<String, Map<String, Object>> longNumberMap = new LinkedHashMap<>();

		Map<String, Object> stringValueMap = new HashMap<>();
		stringValueMap.put("Adams", "Adithya");
		stringValueMap.put("Edge", 80);

		longNumberMap.put("1488376800000", stringValueMap);

		return longNumberMap;
	}

	private void convertMapToKeySetToArray(Object obj) {
		Map<Long, Map<String, Object>> mapOfNumbers = (Map<Long, Map<String, Object>>) obj;
		Set<Long> stringNumbers = mapOfNumbers.keySet();

		convertLongKeySetToArray(stringNumbers);
	}

	private void convertLongKeySetToArray(Set<Long> stringNumbers) {
		Long[] stringNumbersArray = stringNumbers.toArray(new Long[0]);
		Arrays.sort(stringNumbersArray);

		System.out.println(stringNumbersArray);
	}

	private Set<String> prepareStringNumbersSetFromMapOfMap() {
		Map<String, Map<String, Object>> longNumberMap = new LinkedHashMap<>();

		Map<String, Object> stringValueMap = new HashMap<>();
		stringValueMap.put("Adams", "Adithya");
		stringValueMap.put("Edge", 80);

		longNumberMap.put("1488376800000", stringValueMap);

		return longNumberMap.keySet();
	}

	private Set<Long> prepareNumbersSetFromMap() {
		Map<Long, String> longNumberMap = new LinkedHashMap<>();
		longNumberMap.put(1488376800000l, "Adithya");

		return longNumberMap.keySet();
	}

	private Set<Long> prepareNumbersSetFromMapOfMap() {
		Map<Long, Map<String, Object>> longNumberMap = new LinkedHashMap<>();

		Map<String, Object> stringValueMap = new HashMap<>();
		stringValueMap.put("Adams", "Adithya");
		stringValueMap.put("Edge", 80);

		longNumberMap.put(1488376800000l, stringValueMap);

		return longNumberMap.keySet();
	}

	private Set<Long> prepareNumbersSet() {
		Set<Long> longNumbers = new LinkedHashSet<>();
		longNumbers.add(1488376800000l);

		return longNumbers;
	}

	private void convertKeySetToArray(Set<Long> longNumbers) {
		Long[] longNumbersArray = longNumbers.toArray(new Long[0]);
		Arrays.sort(longNumbersArray);

		System.out.println(longNumbers);
	}

	private void convertStringKeySetToArray(Set<String> stringNumbers) {

		Iterator<String> stringKeys = stringNumbers.iterator();
		Long[] keySetArray = new Long[stringNumbers.size()];
		int count = 0;
		while (stringKeys.hasNext()) {
			String key = stringKeys.next();
			keySetArray[count++] = Long.valueOf(key);
		}

		Arrays.sort(keySetArray);

		System.out.println(keySetArray);
	}

}
