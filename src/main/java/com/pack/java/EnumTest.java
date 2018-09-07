package com.pack.java;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnumTest {

	public static void main(String[] args) {
		
		Map<String, Temp> map = new HashMap<>();
		for(Temp temp : DaysOfWeek.values()) {
			map.put(temp.getName(), temp);
		}
		new EnumTest().testDaysOfWeek(map);
		Integer value = 1000;
		String strValue = "1000";
		String[] valueArray = new String[] {"1000", "2000"};
		new EnumTest().test(value);
		new EnumTest().test(Integer.valueOf( strValue ));
		new EnumTest().test(Integer.valueOf(valueArray[0]));
	}
	
	private void test(int number) {
		
	}

	private void testDaysOfWeek(Map<String, Temp> enumDataMap) {
		Collection<Temp> values = enumDataMap.values();
		System.out.println(values.toString());
		for(Temp tempDay : enumDataMap.values()) {
			
			if(tempDay.getName().equals(DaysOfWeek.MONDAY.toString())) {
				System.out.println(tempDay.getDayType());
			}
		}
	}
}

interface Temp {
	String getDayType();
	String getName();
}

enum DaysOfWeek implements Temp {
	MONDAY("working"),FRIDAY("partying"),SUNDAY("relaxing");
	
	private String dayType;
	
	private DaysOfWeek(String dayType) {
		this.dayType = dayType;
	}

	public String getDayType() {
		return dayType;
	}
	
	public String getName() {
		return this.name();
	}

//	public void setDayType(String dayType) {
//		this.dayType = dayType;
//	}
}