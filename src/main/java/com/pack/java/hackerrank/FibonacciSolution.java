package com.pack.java.hackerrank;

import java.util.Scanner;

public class FibonacciSolution {

    public static void main(String[] args) {

        new FibonacciSolution().fibonacci();
    }

    private void fibonacci() {
        Scanner scanner = new Scanner(System.in);
        String line = null;
        int limit;
        while(true) {
            System.out.println("Enter limit to calculate fibonacci series or 'exit' to complete");
            line = scanner.nextLine();
            
            if(line.equalsIgnoreCase("exit")) {
                break;
            } 
            
            limit = Integer.valueOf(line);
            System.out.println("Fibonacci series is ");
            calculate(limit, 0, 1);
        }
    }
    
    //1,1,2,3,5,8,13,21,34
    private void calculate(int limit, int previousNumber, int currentNumber) {
        if(limit >= 0) {
            System.out.println(currentNumber);
            calculate(--limit, currentNumber, previousNumber + currentNumber );
        }
    }

}
