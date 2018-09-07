package com.pack.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListRemovalTest {

	public static void main(String[] args) {
		List<String> stringList = new ArrayList<>();
		stringList.add("aar");
		stringList.add("adams");
		stringList.add("24");
		stringList.add("bones");
		Iterator<String> stringItr = stringList.iterator();
		String tempString = null;
		while(stringItr.hasNext()) {
			tempString = stringItr.next();
			if(tempString.equals("24") || tempString.equals("adams")) {
				stringItr.remove();
			}
		}
		
		System.out.println(stringList.toString());
	}

}
