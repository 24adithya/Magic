package com.pack.java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksTest {

    public static void main(String[] args) {
        List<Integer> contents = new ArrayList<>();
        Lock lock = new ReentrantLock();
        String greenColor = "\u001B[32m";
        String yellowColor = "\u001B[33m";
        String redColor = "\u001B[31m";
        MyProducer myProducer = new MyProducer(greenColor, contents, lock);
        MyConsumer myConsumer1 = new MyConsumer(yellowColor, contents, lock);
        MyConsumer myConsumer2 = new MyConsumer(redColor, contents, lock);

        new Thread(myProducer).start();
        new Thread(myConsumer1).start();
        new Thread(myConsumer2).start();
    }
}

class MyProducer implements Runnable {

    private String color;
    private List<Integer> contents;
    private Lock lock;

    public MyProducer(String color,
                      List<Integer> contents,
                      Lock lock) {
        this.color = color;
        this.contents = contents;
        this.lock = lock;
    }

    @Override
    public void run() {
// contents = Stream.iterate(1, number -> number + 1).limit(5).collect(Collectors.toList());TODO
        Integer[] numbers = new Integer[] { 1, 2, 3, 4, 5 };
        if (lock.tryLock()) {
            try {
// Arrays.stream(numbers).forEach((number) -> contents.add(number));TODO
                for (Integer number : numbers) {
                    try {
                        contents.add(number);
                        System.out.println(color + "Adding " + number);
                    } finally {
                        lock.unlock();
                    }
                    Thread.sleep(1000l);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyConsumer implements Runnable {

    private String color;
    private List<Integer> contents;
    private Lock lock;

    public MyConsumer(String color,
                      List<Integer> contents,
                      Lock lock) {
        this.color = color;
        this.contents = contents;
        this.lock = lock;
    }

    @Override
    public void run() {
// contents = Stream.iterate(1, number -> number + 1).limit(5).collect(Collectors.toList());TODO
        while (true) {
            if (lock.tryLock()) {
                try {
                    if (contents.size() > 0) {
                        for (Integer content : contents) {
                            System.out.println(color + "Consuming + " + content);
                        }
// contents.forEach((number) -> System.out.println("Consuming " + number));
                    } else {
                        continue;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}