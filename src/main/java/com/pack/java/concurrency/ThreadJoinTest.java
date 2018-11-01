package com.pack.java.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadJoinTest {

    public static void main(String[] args) {
        System.out.println("Enter number of threads to create");
        /*Scanner scanner = new Scanner(System.in);*/
        new ThreadJoinTest().testJoin(10);
    }

    private void testJoin(int threadCount) {
        Thread[] threads = new Thread[threadCount];
        
        List<String> listPopulater = new CopyOnWriteArrayList<String>();//CopyOnWrite
        
        List<String> runnableNames = new ArrayList<>();
        runnableNames.add("Alpha");
        runnableNames.add("Beta");
        runnableNames.add("Gamma");
        runnableNames.add("Delta");
        runnableNames.add("Theta");
        runnableNames.add("Trigo");
        runnableNames.add("Derivative");
        runnableNames.add("Integral");
        runnableNames.add("Limits");
        runnableNames.add("Adithya");
        
        int count = 0;
        Thread thread = null;
        for(int cnt = 0;cnt < threadCount ; cnt++){
            thread = new Thread(new ListPopulater(listPopulater, count, runnableNames.remove(0)));
            threads[cnt] = thread;
            thread.start();
            count = count + 10;
        }
        
        Arrays.stream(threads).forEach(currentThread -> {
            try {
                currentThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        listPopulater.forEach(System.out::println);
        
    }
    
    private class ListPopulater implements Runnable {

        private String name;
        private List<String> list;
        private Integer startPoint;
        
        public ListPopulater(List<String> list, Integer startPoint, String name) {
            this.list = list;
            this.startPoint = startPoint;
            this.name = name;
        }
        
        @Override
        public void run() {
            for(int count = startPoint ; count < startPoint + 1000 ; count++) {
                list.add(this.name + ":" +count );
            }
            /*try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                System.out.println(this.name + " interrupted");
                e.printStackTrace();
            }*/
        }
    }
    
    /*private class MapPopulater implements Runnable {

        private String name;
        private Map<Integer, String> map;
        private Integer startPoint;
        
        public MapPopulater(Map<Integer, String> map, Integer startPoint, String name) {
            this.map = map;
            this.startPoint = startPoint;
            this.name = name;
        }
        
        @Override
        public void run() {
            for(int count = startPoint ; count < 10 ; count++) {
                map.put(count, )
            }
        }
    }*/
}
