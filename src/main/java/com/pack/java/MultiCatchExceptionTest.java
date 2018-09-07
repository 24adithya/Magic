package com.pack.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class MultiCatchExceptionTest {

	public static void main(String[] args) throws SQLException, IOException
	{
		MultiCatchExceptionTest test = new MultiCatchExceptionTest();
		test.testMultiCatch();
	}

	private void testMultiCatch() throws SQLException, IOException
	{
		try
		{
			couldThrowAnException();
		}
		catch (Exception e)
		{ // watch out: this isn't really
			// catching all exception subclasses
			log(e);
			throw e; // note: won't compile in Java 6
		}
	}

	private void log(Exception e)
	{
		System.out.println("File not Found !!");
	}

	private void couldThrowAnException() throws IOException
	{
		try (/*Reader reader =
				new BufferedReader(new FileReader(new File("c:\\Adams")));*/
				One one = new One();Two two = new Two();) 
		{
			// do stuff
		}
	}
}

class One implements AutoCloseable
{
	@Override
	public void close() throws IOException
	{
		System.out.println("Closing One.");
	}
}

class Two implements AutoCloseable
{
	@Override
	public void close() throws IOException
	{
		System.out.println("Closing Two.");
	}
}
