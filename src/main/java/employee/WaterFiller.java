package employee;

import aquarium.*;

public class WaterFiller {
	Water water;

	public static void main(String[] args) {
//		byte a = 40, b = 50;
//		byte sum = a + b;
//		System.out.println(sum);
		
		walk2(10, new int[]{1,2,3}, new int[]{4,5});
	}
	
	final static public  String t(){
		return "";
	}
	
	public static void walk2(int start, Object... nums) {
		System.out.println(start);
		System.out.println(nums.length);
	}
}
