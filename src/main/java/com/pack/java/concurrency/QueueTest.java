package com.pack.java.concurrency;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        
        for(int i = 1; i < 6 ; i++) {
            queue.add(""+i);
        }
        
//        queue.iterator().forEachRemaining(System.out::println);
        Iterator<String> iterator = queue.iterator();
        while(iterator.hasNext()) {
            System.out.print( iterator.next() );
        }
        
        
    }

}
