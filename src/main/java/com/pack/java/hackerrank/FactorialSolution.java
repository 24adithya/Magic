package com.pack.java.hackerrank;

import java.util.Scanner;

public class FactorialSolution {

    public static void main(String[] args) {

        new FactorialSolution().factorial();
    }

    private void factorial() {
        Scanner scanner = new Scanner(System.in);
        String line = null;
        double number;
        while(true) {
            line = scanner.nextLine();
            
            if(line.equalsIgnoreCase("exit")) {
                break;
            } 
            
            number = Double.valueOf(line);
            System.out.println("Factorial of " + number +" is " + calculate(number));
        }
    }
    
    private double calculate(Double number) {
        Double total = 1.0d;
        if(number >= 1) {
            total = number * calculate(number-1);
        }
        return total;
    }

}
