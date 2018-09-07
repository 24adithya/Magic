package com.pack.java.streams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class StreamReaderTest {
	public static void main(String[] args)
	{
		StreamReaderTest streamReaderTest = new StreamReaderTest();
		try
		{
			streamReaderTest.testStreamReader();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void testStreamReader() throws IOException, ParserConfigurationException, SAXException
	{
		InputStream inputStream = new FileInputStream(".\\res\\test.xml");
		Charset cs = Charset.forName("UTF-8");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, cs);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
//		IOUtils utils = new IOUtils();
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer);
		System.out.println(writer.toString());
		//Reading from buffered stream
		while(inputStream.read() != -1) 
		{
			System.out.print((char)inputStream.read());
		}
		
		//Reading from buffered reader
		while(bufferedReader.read() != -1) 
		{
			System.out.println(bufferedReader.readLine());
		}
	}
}
