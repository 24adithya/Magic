package com.pack.java.exceptions;

public class ExceptionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(exceptions());
	}

	public static String exceptions() {
		String result = "";
		String v = null;
		try {
			try {
				result += "before";
				v.length();
				result += "after";
			} catch (NullPointerException e) {
				result += "catch";
				throw new RuntimeException();
			} finally {
				result += "finally";
				throw new Exception();
			}
		} catch (Exception e) {
			result += "done";
		}
		return result;
	}

}
