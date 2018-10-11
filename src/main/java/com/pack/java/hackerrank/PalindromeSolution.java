package com.pack.java.hackerrank;

import java.util.Scanner;

public class PalindromeSolution {

    public static void main(String[] args) {

        new PalindromeSolution().isPalindrome();
    }

    private void isPalindrome() {
        Scanner scanner = new Scanner(System.in);
        String line = null;
        Integer fwdCount, revCount;
        boolean isPalindrome = true;
        while(true) {
            isPalindrome = true;
            line = scanner.nextLine();
            
            if(line.equalsIgnoreCase("exit")) {
                break;
            } 
            
            fwdCount = 0;
            revCount = line.length() - 1;
            
            while(fwdCount < line.length()) {
                if(line.charAt(fwdCount) != line.charAt(revCount)) {
                    isPalindrome = false;
                    break;
                }
                
                fwdCount ++;
                revCount --;
            }
            
            if(isPalindrome) {
                System.out.println("" + line + " is Palindrome");
            } else {
                System.out.println("" + line + " is not a Palindrome");
            }
        }
    }
}
