package com.pack.java.hackerrank;

import java.util.Arrays;
import java.util.Scanner;
/*
 * test cases:
 * money = 4
 * 1 4 5 3 2
 * 
 * expected output in indices
 * 
 * 1 4 i.e. values 1 and 3
 * solution
 * 
 * sorted - 1 2 3 4 5
 */
public class SearchSolution {
	
	// Complete the whatFlavors function below.
	static int[] whatFlavors(int[] cost, int money) {

		int[] indices = new int[2];
		int[] sortedCost = cost.clone();
		Arrays.sort(sortedCost);
		int complement, complementLocation;
		for (int count = 0; count < sortedCost.length; count++) {
			complement = money - sortedCost[count];
			complementLocation = Arrays.binarySearch(sortedCost, count + 1, sortedCost.length, complement);
			if(complementLocation >=0 && complementLocation < sortedCost.length && sortedCost[complementLocation] == complement) {
				indices = getIndicesFromValues(cost, sortedCost[count], complement);
				
				count = 0;
				for(int index : indices) {
					if(count > 0) {
						System.out.print(" ");
					}
					System.out.print(index);
					count++;
				}
				System.out.println();
					
				break;
			}
		}
		return indices;
	}

	private static int[] getIndicesFromValues(int[] cost, int value1, int value2) {
		int index1 = getIndexOf(cost, value1, -1);
		int index2 = getIndexOf(cost, value2, index1 - 1);
		
		return new int[] {Math.min(index1, index2), Math.max(index1, index2)};
	}

	private static int getIndexOf(int[] cost, int value, int ignoreIndex) {
		int count = 0;
		for( ;count < cost.length ; count++) {
			if(cost[count] == value && count != ignoreIndex) {
				break;
			}
		}
		return count + 1;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
	    System.out.println("Enter input number");
		int t = scanner.nextInt();
		
		
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
		    System.out.println("Enter money");
			int money = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			System.out.println("Enter total flavors");
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[] cost = new int[n];

			System.out.println("Enter flavors");
			String[] costItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < n; i++) {
				int costItem = Integer.parseInt(costItems[i]);
				cost[i] = costItem;
			}

			whatFlavors(cost, money);
		}

		scanner.close();
	}
}
