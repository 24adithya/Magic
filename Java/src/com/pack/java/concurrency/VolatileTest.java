package com.pack.java.concurrency;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileTest {
    private volatile boolean run = true;

    public static void main(String[] args) {

        VolatileTest locksTest = new VolatileTest();
        Queue<String> contents = new LinkedList<>();

        String greenColor = "\u001B[32m";
        String yellowColor = "\u001B[33m";
        String redColor = "\u001B[31m";

        MyVolatileProducer myProducer = new MyVolatileProducer(greenColor, contents, locksTest);
        MyVolatileConsumer myConsumer1 = new MyVolatileConsumer(yellowColor, contents, locksTest);
        MyVolatileConsumer myConsumer2 = new MyVolatileConsumer(redColor, contents, locksTest);

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(myProducer);
        service.execute(myConsumer2);
        service.execute(myConsumer1);
        service.shutdown();

// new Thread(myProducer).start();
// new Thread(myConsumer1).start();
// new Thread(myConsumer2).start();

    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}

class MyVolatileProducer implements Runnable {

    private String color;
    private Queue<String> contents;
    private VolatileTest locksTest;

    public MyVolatileProducer(String color,
                      Queue<String> contents,
                      VolatileTest locksTest) {
        this.color = color;
        this.contents = contents;
        this.locksTest = locksTest;
    }

    @Override
    public void run() {

        int count = 0;
        while (locksTest.isRun()) {
            contents.add("" + ++count);
            System.out.println(color + "Adding " + count);
            if (count == 5) {
                System.out.println(color + "Adding " + "EOF");
                contents.add("EOF");
                break;
            }

            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class MyVolatileConsumer implements Runnable {

    private String color;
    private Queue<String> contents;
    private VolatileTest locksTest;

    public MyVolatileConsumer(String color,
                      Queue<String> contents,
                      VolatileTest locksTest) {
        this.color = color;
        this.contents = contents;
        this.locksTest = locksTest;
    }

    @Override
    public void run() {
        String value = null;
        while (locksTest.isRun()) {
            if (!contents.isEmpty()) {
                Iterator<String> stringItr = contents.iterator();

                while (stringItr.hasNext()) {
                    value = stringItr.next();
                    System.out.println(color + "Consuming " + value);
                    stringItr.remove();
                }
            }
            if ("EOF".equals(value)) {
                locksTest.setRun(false);
                break;
            }
        }
    }
}