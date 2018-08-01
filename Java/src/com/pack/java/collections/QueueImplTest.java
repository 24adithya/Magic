package com.pack.java.collections;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueImplTest {

	public static void main(String[] args) {
		Queue<Integer> priorityQueue = new PriorityQueue<>(new PriorityQueueComparator());
		priorityQueue.offer(10);
		priorityQueue.offer(20);
		priorityQueue.offer(15);
		priorityQueue.offer(13);
		
		priorityQueue.forEach(System.out::println);
	}
}

class PriorityQueueComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer integer1, Integer integer2) {
		return integer1.compareTo(integer2);
	}
	
}