package com.pack.java.concurrency;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Good example to keep the consumer threads running till the volatile variable is true.
 * @author Adithya Narayan
 *
 */
public class VolatileTest {
    private volatile boolean run = true;
    private String name = "Adithya";

    public static void main(String[] args) {

        VolatileTest locksTest = new VolatileTest();
        Queue<String> contents = new LinkedList<>();

        String greenColor = "\u001B[32m";
        String yellowColor = "\u001B[33m";
        String redColor = "\u001B[31m";

        MyVolatileProducer myProducer = new MyVolatileProducer(greenColor, contents, locksTest, "Producer");
        MyVolatileConsumer myConsumer1 = new MyVolatileConsumer(yellowColor, contents, locksTest, "Consumer1");
        MyVolatileConsumer myConsumer2 = new MyVolatileConsumer(redColor, contents, locksTest, "Consumer2");

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class MyVolatileProducer implements Runnable {

    private String color;
    private Queue<String> contents;
    private VolatileTest locksTest;

    public MyVolatileProducer(String color,
                      Queue<String> contents,
                      VolatileTest locksTest, String name) {
        Thread.currentThread().setName(name);
        System.out.println(color + "Thread running " + Thread.currentThread().getName());
        this.color = color;
        this.contents = contents;
        this.locksTest = locksTest;
    }

    @Override
    public void run() {

        int count = 0;
//        locksTest.setRun(false); 
        /*
         * This will kill both the consumer threads 'if they have started'. If any consumer thread starts after the variable is set then it will continue till the volatile variable is true! As of now, i am able to verify using the sysouts
         */ 
        while (count <= 5) {
            contents.add("" + ++count);
            if(count >= 3) {
                locksTest.setRun(false);
                System.out.println(color + Thread.currentThread().getName() + " setting 'run' to " + locksTest.isRun());
                locksTest.setName("AAR");
            }
            
            System.out.println(color + "Adding " + count);
            locksTest.setRun(true);
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(color + "Adding " + "EOF");
        contents.add("EOF");
        contents.add("EOF");
        locksTest.setRun(true);
    }
}

class MyVolatileConsumer implements Runnable {

    private String color;
    private Queue<String> contents;
    private VolatileTest locksTest;

    public MyVolatileConsumer(String color,
                      Queue<String> contents,
                      VolatileTest locksTest, String name) {
        Thread.currentThread().setName(name);
        System.out.println(color + "Thread running " + Thread.currentThread().getName());
        this.color = color;
        this.contents = contents;
        this.locksTest = locksTest;
    }

    @Override
    public void run() {
        String value = null;
        while (locksTest.isRun()) {
            System.out.println(color + Thread.currentThread().getName() + "  'run' value is " + locksTest.isRun());
            if (!contents.isEmpty()) {
                Iterator<String> stringItr = contents.iterator();

                while (stringItr.hasNext()) {
                    value = stringItr.next();
                    System.out.println(color + "Consuming " + value);
                    stringItr.remove();
                    
                    if ("EOF".equals(value)) {
                        System.out.println(color + "Exiting.." + value);
                        locksTest.setRun(false);
                        break;
                    }
                }
            }
        }
    }
}