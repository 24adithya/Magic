package com.pack.java.concurrency;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LinkedBlockingQueueTestProdCons {

    private transient Logger logger = LogManager.getLogger(LinkedBlockingQueueTestProdCons.class.getCanonicalName());

    public static void main(String[] args) {

        LinkedBlockingQueueTestProdCons locksTest = new LinkedBlockingQueueTestProdCons();

        BlockingQueue<String> contents = new LinkedBlockingQueue<>();

        String greenColor = "\u001B[32m";
        String yellowColor = "\u001B[33m";
        String redColor = "\u001B[31m";

        MyReEntrantProducerBlocking myProducer = new MyReEntrantProducerBlocking(greenColor, locksTest, contents, "Producer");
        MyReEntrantConsumerBlocking myConsumer1 = new MyReEntrantConsumerBlocking(yellowColor, locksTest, contents, "Consumer 1");
        MyReEntrantConsumerBlocking myConsumer2 = new MyReEntrantConsumerBlocking(redColor, locksTest, contents, "Consumer 2");

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(myProducer);
        service.execute(myConsumer2);
        service.execute(myConsumer1);
        service.shutdown();

// new Thread(myProducer).start();
// new Thread(myConsumer1).start();
// new Thread(myConsumer2).start();

    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}

class MyReEntrantProducerBlocking implements Runnable {

    private String color;
    private BlockingQueue<String> contents;
    private String name;
    private LinkedBlockingQueueTestProdCons locksTest;

    public MyReEntrantProducerBlocking(String color,
                                       LinkedBlockingQueueTestProdCons locksTest,
                                       BlockingQueue<String> contents,
                                       String name) {
        this.color = color;
        this.locksTest = locksTest;
        this.contents = contents;
        this.name = name;
    }

    @Override
    public void run() {

        Thread.currentThread().setName(name);
        locksTest.getLogger().info(color + Thread.currentThread().getName() + " running");
        int count = 0;
        while (count <= 5) {

            try {
                locksTest.getLogger().debug(color + Thread.currentThread().getName() + " about to acquire lock ");
                contents.put("" + ++count);
                locksTest.getLogger().debug(color + "Adding " + count);

                if (count == 5) {
                    locksTest.getLogger().debug(color + "Adding " + "EOF");
                    contents.put("EOF");
                    locksTest.getLogger().debug(color + "Adding " + "EOF");
                    contents.put("EOF");
                    break;
                }

                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyReEntrantConsumerBlocking implements Runnable {

    private String color;
    private BlockingQueue<String> contents;
    private LinkedBlockingQueueTestProdCons locksTest;
    private String name;

    public MyReEntrantConsumerBlocking(String color,
                                       LinkedBlockingQueueTestProdCons locksTest,
                                       BlockingQueue<String> contents,
                                       String name) {
        this.color = color;
        this.contents = contents;
        this.locksTest = locksTest;
        this.name = name;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        locksTest.getLogger().info(color + Thread.currentThread().getName() + " running");
        String value = null;
        while (value != "EOF") {
            if (!contents.isEmpty()) {
                for(String element : contents) {
                    locksTest.getLogger().debug(color + "Consuming " + element);
                    
                    try {
                        contents.take();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    if ("EOF".equals(element)) {
                        value = element;
                        locksTest.getLogger().debug(color + "Exiting..");
                        break;
                    }
                }
                /*Iterator<String> stringItr = contents.iterator();

                while (stringItr.hasNext()) {
                    value = stringItr.next();
                    locksTest.getLogger().debug(color + "Consuming " + value);
                    stringItr.remove();

                    if ("EOF".equals(value)) {
                        locksTest.getLogger().debug(color + "Exiting..");
                        break;
                    }
                }*/
            }
        }
    }
}