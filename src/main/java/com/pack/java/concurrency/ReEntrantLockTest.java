package com.pack.java.concurrency;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReEntrantLockTest {
    private ReentrantLock lockContents;
    
    private transient Logger logger = LogManager.getLogger(ReEntrantLockTest.class.getCanonicalName());

    public static void main(String[] args) {

        ReEntrantLockTest locksTest = new ReEntrantLockTest();
        
        locksTest.setLockContents(new ReentrantLock(true));
        Queue<String> contents = new LinkedList<>();

        String greenColor = "\u001B[32m";
        String yellowColor = "\u001B[33m";
        String redColor = "\u001B[31m";

        MyReEntrantProducer myProducer = new MyReEntrantProducer(greenColor, contents, locksTest, "Producer");
        MyReEntrantConsumer myConsumer1 = new MyReEntrantConsumer(yellowColor, contents, locksTest, "Consumer 1");
        MyReEntrantConsumer myConsumer2 = new MyReEntrantConsumer(redColor, contents, locksTest, "Consumer 2");

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(myProducer);
        service.execute(myConsumer2);
        service.execute(myConsumer1);
        service.shutdown();

// new Thread(myProducer).start();
// new Thread(myConsumer1).start();
// new Thread(myConsumer2).start();

    }

    public ReentrantLock getLockContents() {
        return lockContents;
    }

    public void setLockContents(ReentrantLock lockContents) {
        this.lockContents = lockContents;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}

class MyReEntrantProducer implements Runnable {

    private String color;
    private Queue<String> contents;
    private ReentrantLock lock;
    private ReEntrantLockTest locksTest;
    private String name;

    public MyReEntrantProducer(String color,
                      Queue<String> contents,
                      ReEntrantLockTest locksTest, String name) {
        this.color = color;
        this.contents = contents;
        this.lock = locksTest.getLockContents();
        this.name = name;
        this.locksTest = locksTest;
    }

    @Override
    public void run() {

        Thread.currentThread().setName(name);
        locksTest.getLogger().info( color + Thread.currentThread().getName() + " running" ); 
        int count = 0;
        while (count <= 5) {
            locksTest.getLogger().debug(color + Thread.currentThread().getName() + " about to acquire lock ");
            if(lock.tryLock()) {
                try {
                    locksTest.getLogger().debug( color + Thread.currentThread().getName() + " hold count is " +  lock.getHoldCount() );
                    contents.add("" + ++count);
                    locksTest.getLogger().debug( color + "Adding " + count);
           
                    if (count == 5) {
                        locksTest.getLogger().debug( color + "Adding " + "EOF");
                        contents.add("EOF");
                        contents.add("EOF");
//                        lock.unlock();
                        locksTest.getLogger().debug( color + Thread.currentThread().getName() + " hold count after exit is " +  lock.getHoldCount() );
                        break;
                    }
                    
//                    lock.unlock();
                    locksTest.getLogger().debug( color + Thread.currentThread().getName() + " hold count after release is " +  lock.getHoldCount() );
                } finally {
                    lock.unlock();
                }
            }
            
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyReEntrantConsumer implements Runnable {

    private String color;
    private Queue<String> contents;
    private ReentrantLock lock;
    private String name;
    private ReEntrantLockTest locksTest;

    public MyReEntrantConsumer(String color,
                      Queue<String> contents,
                      ReEntrantLockTest locksTest, String name) {
        
        this.color = color;
        this.contents = contents;
        this.lock = locksTest.getLockContents();
        this.name = name;
        this.locksTest = locksTest;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        locksTest.getLogger().info(color +  Thread.currentThread().getName() + " running" );
        String value = null;
        while (value != "EOF") {
            locksTest.getLogger().debug( color + Thread.currentThread().getName() + " about to acquire lock ");
            if(lock.tryLock()) {
                try {
                    locksTest.getLogger().debug( color +  Thread.currentThread().getName() + " hold count is " +  lock.getHoldCount() );
                    if (!contents.isEmpty()) {
                        Iterator<String> stringItr = contents.iterator();

                        while (stringItr.hasNext()) {
                            value = stringItr.next();
                            locksTest.getLogger().debug( color + "Consuming " + value);
                            stringItr.remove();
                            
                            if ("EOF".equals(value)) {
                                locksTest.getLogger().debug( color + "Exiting..");
                                break;
                            }
                        }
                    }
//                    lock.unlock();
                    locksTest.getLogger().debug( color + Thread.currentThread().getName() + " hold count after release is " +  lock.getHoldCount() );
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}