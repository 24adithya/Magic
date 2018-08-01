package com.pack.java.datastructures;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class QuickSort {

	public static void main(String[] args) {
		int[] array = new int[] { 9, 6, 4, 5, 2, 7, 3, 1, 8 };
		new QuickSort().quickSort(array, 0, array.length - 1);

		IntStream.of(array).forEach(System.out::println);
		;
	}

	private void quickSort(int[] array, int start, int end) {
		int left = start, right = end; 
		if (left >= right) {
			return;
		}

		int pivot = array[(left + right) / 2];

		while (left <= right) {
			while (array[left] < pivot) {
				left++;
			}

			while (array[right] > pivot) {
				right--;
			}

			if (left <= right) {
				swap(left, right, array);
				left++;
				right--;
			}
		}

		quickSort(array, start, left - 1);
		quickSort(array, left, end);

		// int pIndex = partitionAndSwap(array, left, right, pivot);
	}

	private void swap(int left, int right, int[] array) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	private int partitionAndSwap(int[] array, int left, int right, int pivot) {
		return -1;
	}
}
