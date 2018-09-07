package com.pack.java;

import java.io.Console;

public class ConsoleTest {

	public static void main(String[] args) {

		Console sysConsole  = System.console();
		String input = sysConsole.readLine("Enter name : ");
		System.out.println("input is " + input);
		
		System.out.println("blah");
		System.out.println("blah");
	}

}
