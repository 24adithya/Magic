package com.pack.java.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SubstringSolution {

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
    	
    	boolean matchFound = false;
    	for(char c : s1.toCharArray()) {
    		if (s2.contains(String.valueOf(c))) {
    			matchFound = true;
    			break;
    		}
    	}
    	
    	if(matchFound)
    		return "YES";
    	else return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//    	System.out.println(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

//        int q = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

//        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
//        }

        bufferedWriter.close();

        scanner.close();
    }
}

