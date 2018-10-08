package com.pack.java.puzzles;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrimeNumberTest {
    
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String input;
        Integer inputNumber;
        while( true ) {
            input = inputScanner.nextLine();
            try {
                inputNumber = Integer.valueOf(input);
                new PrimeNumberTest().isPrime(inputNumber);
            } catch(NumberFormatException nfe) {
                if(input.equalsIgnoreCase("done")) {
                    break;
                }
            }
        }
    }

    private void isPrime(Integer inputNumber) {
        boolean isPrime = true;
        for(int count = 1; count <= inputNumber ;count++ ) {
           
           if( count == inputNumber ) {
               break;
           }
           
           if(inputNumber % count == 0 && count != 1) {
               isPrime = false;
               break;
           }
        }
        
        if(isPrime) {
            System.out.println("" + inputNumber + " is prime");
        } else {
            System.out.println("" + inputNumber + " is not prime");
        }
    }

   
}
