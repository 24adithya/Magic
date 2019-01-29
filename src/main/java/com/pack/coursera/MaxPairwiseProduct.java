package com.pack.coursera;

import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static Integer getMaxPairwiseProduct(Integer[] numbers) {
    	int max_product = 0;
    	if(numbers != null && numbers.length >=2 ) {
            int n = numbers.length;
            Arrays.sort(numbers, Collections.reverseOrder());
            
            max_product = numbers[0] * numbers[1]; 
    	}

        return max_product;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        Integer n = scanner.nextInt();
        Integer[] numbers = new Integer[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            return Integer.valueOf(next());
        }
    }

}