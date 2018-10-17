package com.pack.java.puzzles;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PalindromeOrAnagramOfPalindromeTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			String word = scanner.nextLine();
			
			if(word.equalsIgnoreCase("done")) {
				break;
			}
			
			PalindromeOrAnagramOfPalindromeTest test = new PalindromeOrAnagramOfPalindromeTest();
			boolean isPalindrome = test.isPalindrome(word);
			boolean isAnagramOfPalindrome = false;
			if(!isPalindrome) {
				isAnagramOfPalindrome = test.isAnagramOfPalindrome(word);
			}
			
			System.out.println("Is " + word + " a palindrome ? " + isPalindrome);
			System.out.println("Is " + word + " an anagram of palindrome ? " + isAnagramOfPalindrome);
		}
	}

	private boolean isPalindrome(String word) {
        Integer fwdCount, revCount;
        boolean isPalindrome = true;
            isPalindrome = true;
            
            fwdCount = 0;
            revCount = word.length() - 1;
            
            while(fwdCount < word.length()) {
                if(word.charAt(fwdCount) != word.charAt(revCount)) {
                    isPalindrome = false;
                    break;
                }
                
                fwdCount ++;
                revCount --;
            }
            
            if(isPalindrome) {
                System.out.println("" + word + " is Palindrome");
            } else {
                System.out.println("" + word + " is not a Palindrome");
            }
            
            return isPalindrome;
	}
	
	private boolean isAnagramOfPalindrome(String word) {
		boolean isAnagramOfPalindrome = true;
		/*
		 * Create a map of characters vs its count.
		 */
		Map<String,Long> mapOfElements = Arrays.stream(word.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		int oddCount = 0, evenCount = 0;
		if(!mapOfElements.isEmpty()) {
			for(Long count : mapOfElements.values()) {
				if(count % 2 == 0) {
					evenCount++;
				} else if(count % 1 == 0) {
					oddCount++;
				}  
				
				if(oddCount > 1) {
					isAnagramOfPalindrome = false;
					break;
				}
			}
		}
		
		return isAnagramOfPalindrome;
	}
}
