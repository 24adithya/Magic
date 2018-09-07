package com.pack.java.concurrency;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Best test case for 'synchronized' blocks for multi threading.
 * @author Adithya Narayan
 *
 */
public class SynchronizedTest {
    private volatile boolean run = true;

    public static void main(String[] args) {

        SynchronizedTest locksTest = new SynchronizedTest();
        Queue<String> contents = new LinkedList<>();

        String greenColor = "\u001B[32m";
        String yellowColor = "\u001B[33m";
        String redColor = "\u001B[31m";

        MySynchronizedProducer myProducer = new MySynchronizedProducer(greenColor, contents);
        MySynchronizedConsumer myConsumer1 = new MySynchronizedConsumer(yellowColor, contents);
        MySynchronizedConsumer myConsumer2 = new MySynchronizedConsumer(redColor, contents);

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

class MySynchronizedProducer implements Runnable {

    private String color;
    private Queue<String> contents;

    public MySynchronizedProducer(String color,
                      Queue<String> contents) {
        this.color = color;
        this.contents = contents;
    }

    @Override
    public void run() {

        int count = 0;
        while (count <= 2) {
            synchronized(contents) {
                contents.add("" + ++count);
                System.out.println(color + "Adding " + count);
            }
            //TODO: Uncomment for sequential consumption
            /*try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        
        synchronized(contents) {
            System.out.println(color + "Adding " + "EOF");
            contents.add("EOF");
            contents.add("EOF");
        }
    }
}

class MySynchronizedConsumer implements Runnable {

    private String color;
    private Queue<String> contents;

    public MySynchronizedConsumer(String color,
                      Queue<String> contents) {
        this.color = color;
        this.contents = contents;
    }

    @Override
    public void run() {
        String value = null;
        while (true) {
            
            if ("EOF".equals(value)) {
                break;
            }
            synchronized(contents) {
                if (!contents.isEmpty()) {
                    Iterator<String> stringItr = contents.iterator();

                    while (stringItr.hasNext()) {
                        value = stringItr.next();
                        System.out.println(color + "Consuming " + value);
                        stringItr.remove();
                        if ("EOF".equals(value)) {
                            System.out.println(color + "Exiting.. ");
                            break;
                        }
                    }
                }
                
            }
        }
    }
}