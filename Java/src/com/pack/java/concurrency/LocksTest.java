package com.pack.java.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksTest {

    public static void main(String[] args) {
        List<String> contents = new ArrayList<>();
        Lock lock = new ReentrantLock();
        String greenColor = "\u001B[32m";
        String yellowColor = "\u001B[33m";
        String redColor = "\u001B[31m";
        MyProducer myProducer = new MyProducer(greenColor, contents, lock);
        MyConsumer myConsumer1 = new MyConsumer(yellowColor, contents, lock);
        MyConsumer myConsumer2 = new MyConsumer(redColor, contents, lock);

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(myProducer);
        service.execute(myConsumer2);
        service.execute(myConsumer1);
// new Thread(myProducer).start();
// new Thread(myConsumer1).start();
// new Thread(myConsumer2).start();
        service.shutdown();
    }
}

class MyProducer implements Runnable {

    private String color;
    private List<String> contents;
    private Lock lock;

    public MyProducer(String color,
                      List<String> contents,
                      Lock lock) {
        this.color = color;
        this.contents = contents;
        this.lock = lock;
    }

    @Override
    public void run() {

        int count = 0;
        while (true) {
            if (lock.tryLock()) {
                try {
                    contents.add("" + ++count);
                    System.out.println(color + "Adding " + count);
                    if (count == 5) {
                        System.out.println(color + "Adding " + "EOF");
                        contents.add("EOF");
                        break;
                    }

                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) { // TODO Auto-generated catch block e.printStackTrace();
                    }

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

class MyConsumer implements Runnable {

    private String color;
    private List<String> contents;
    private Lock lock;

    public MyConsumer(String color,
                      List<String> contents,
                      Lock lock) {
        this.color = color;
        this.contents = contents;
        this.lock = lock;
    }

    @Override
    public void run() {
        String value = null;
        while (true) {
            if (lock.tryLock()) {
                try {
                    if (!contents.isEmpty()) {
                        Iterator<String> stringItr = contents.iterator();

                        while (stringItr.hasNext())
                            value = stringItr.next();
                        System.out.println(color + "Consuming " + value);
                        stringItr.remove();
                    }
                    if ("EOF".equals(value)) {
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}