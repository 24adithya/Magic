package com.pack.java.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileExtensionRenameTest {

	public static void main(String[] args) throws IOException {
		FileExtensionRenameTest test = new FileExtensionRenameTest();
		test.renameFile();
	}
	
	public void renameFile() throws IOException {
		String path = "C://adams//";
		String tempLine = null;
//		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
			while((tempLine = reader.readLine()) != null) {
				System.out.println(tempLine);
			}
			
		}
		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(path))) {

			stream.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


