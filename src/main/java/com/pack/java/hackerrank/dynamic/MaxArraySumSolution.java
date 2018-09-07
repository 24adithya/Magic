package com.pack.java.hackerrank.dynamic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxArraySumSolution {

    // Complete the maxSubsetSum function below.
    /*
     * 5
     * 3 7 4 6 5
     * 
     */
    static int maxSubsetSum(int[] array) {
        int result = 0, count = 0, arrayCount = 0;
        List<Integer[]> subsetList = new ArrayList<>();
        List<Integer> tempList =  new ArrayList<>();
        
        int loopCount = 0;
        int increment = 2;
        while(arrayCount < array.length) {
//            loopCount++;
            
            while(count < array.length) {
                tempList.add(array[count]);
                count = count + increment;
            }
            
            //TODO: check for duplicates
            if(tempList.size() > 1) {
                subsetList.add(tempList.toArray(new Integer[0]));
            }
            
            tempList = new ArrayList<>();
            
            //Reinitialize
            if(count >= array.length) {
                increment++; 
                count = arrayCount;
                if(increment == array.length) {
                    increment = 2;
                    count = ++arrayCount;
                }
            }
        }
        
        /*while(arrayCount < array.length) {
            
            //TODO: check for duplicates
            if(tempList.size() > 1) {
                subsetList.add(tempList.toArray(new Integer[0]));
            }
            
            //Reinitialize
            if(count >= array.length) {
                
                tempList = new ArrayList<>();
                arrayCount++;
                count = arrayCount;
            }
            
            //Add the value to the list
            if(count < array.length) {
                tempList.add(array[count]);
                
                count = count + 2;
            }
        }*/
        
        System.out.println();
        
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
