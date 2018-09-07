package com.pack.java.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TwoStringsSolution {

    // Complete the twoStrings function below.
    static String twoStrings(String string1,
                             String string2) {

        String result = "NO";
        String[] string1Array = string1.split("");
        String[] string2Array = string2.split("");
        String[] string2Clone = string2Array.clone();
        Arrays.sort(string2Clone);
        String key = "";

        for (int count = 0; count < string1Array.length; count++) {

            key = string1Array[count];
            if (Arrays.binarySearch(string2Clone, key) >= 0) {
                result = "YES";
                break;
            }
        }

        System.out.println(result);

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
