package com.pack.java.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SherlockAnagramSolution {

    // Complete the sherlockAndAnagrams function below.
//    static int sherlockAndAnagrams(String s) {
    static void sherlockAndAnagrams(String s) {
        
        String[] inputArray = s.split("");
        Map<String[], String[]> anagramMap = new HashMap<>();
        int count = 0;
        String[] tempArray;
        while(count < inputArray.length) {
            
            tempArray = 
            
            count++;
        }
        
//        permutation(s);

    }
    
    public static void permutation(String str) { 
        permutation("", str); 
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

//            int result = sherlockAndAnagrams(s);
//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
            sherlockAndAnagrams(s);

            
        }

        bufferedWriter.close();

        scanner.close();
    }
}

