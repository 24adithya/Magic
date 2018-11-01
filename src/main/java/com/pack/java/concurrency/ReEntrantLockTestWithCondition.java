package com.pack.java.concurrency;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReEntrantLockTestWithCondition {
    private ReentrantLock lockContents;
    private Condition waitingProducer;

    private transient Logger logger = LogManager.getLogger(ReEntrantLockTestWithCondition.class.getCanonicalName());

    public static void main(String[] args) {

        ReEntrantLockTestWithCondition locksTest = new ReEntrantLockTestWithCondition();

        ReentrantLock reEntrantLock = new ReentrantLock(true);
        Condition waitingProducer = reEntrantLock.newCondition();
        locksTest.setLockContents(reEntrantLock);
        locksTest.setWaitingProducer(waitingProducer);

        Queue<String> contents = new LinkedList<>();

        String greenColor = "\u001B[32m";
        String yellowColor = "\u001B[33m";
        String redColor = "\u001B[31m";

        MyReEntrantProducerConditional myProducer = new MyReEntrantProducerConditional(greenColor, contents, locksTest, "Producer");
        MyReEntrantConsumerConditional myConsumer1 = new MyReEntrantConsumerConditional(yellowColor, contents, locksTest, "Consumer 1");
        MyReEntrantConsumerConditional myConsumer2 = new MyReEntrantConsumerConditional(redColor, contents, locksTest, "Consumer 2");

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(myProducer);
        service.execute(myConsumer2);
        service.execute(myConsumer1);
        service.shutdown();

// new Thread(myProducer).start();
// new Thread(myConsumer1).start();
// new Thread(myConsumer2).start();

    }

    public Condition getWaitingProducer() {
        return waitingProducer;
    }

    public void setWaitingProducer(Condition waitingProducer) {
        this.waitingProducer = waitingProducer;
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

class MyReEntrantProducerConditional implements Runnable {

    private String color;
    private Queue<String> contents;
    private ReentrantLock lock;
    private Condition waitingProducer;
    private ReEntrantLockTestWithCondition locksTest;
    private String name;

    public MyReEntrantProducerConditional(String color,
                                          Queue<String> contents,
                                          ReEntrantLockTestWithCondition locksTest,
                                          String name) {
        this.color = color;
        this.contents = contents;
        this.lock = locksTest.getLockContents();
        this.name = name;
        this.locksTest = locksTest;
        this.waitingProducer = locksTest.getWaitingProducer();
    }

    @Override
    public void run() {

        Thread.currentThread().setName(name);
        locksTest.getLogger().info(color + Thread.currentThread().getName() + " running");
        int count = 0;
        while (count <= 5) {

                locksTest.getLogger().debug(color + Thread.currentThread().getName() + " about to acquire lock ");
                if (lock.tryLock()) {
                    try {
                        locksTest.getLogger().debug(color + Thread.currentThread().getName() + " hold count is " + lock.getHoldCount());
                        contents.add("" + ++count);
                        locksTest.getLogger().debug(color + "Adding " + count);

                        waitingProducer.signal();
                        if (count == 5) {
                            locksTest.getLogger().debug(color + "Adding " + "EOF");
                            contents.add("EOF");
                            contents.add("EOF");
                            waitingProducer.signal();
//                        lock.unlock();
                            locksTest.getLogger().debug(color + Thread.currentThread().getName() + " hold count after exit is " + lock.getHoldCount());
                            break;
                        }

//                    lock.unlock();
                        locksTest.getLogger().debug(color + Thread.currentThread().getName() + " hold count after release is " + lock.getHoldCount());
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

class MyReEntrantConsumerConditional implements Runnable {

    private String color;
    private Queue<String> contents;
    private ReentrantLock lock;
    private String name;
    private Condition waitingProducer;
    private ReEntrantLockTestWithCondition locksTest;

    public MyReEntrantConsumerConditional(String color,
                                          Queue<String> contents,
                                          ReEntrantLockTestWithCondition locksTest,
                                          String name) {

        this.color = color;
        this.contents = contents;
        this.lock = locksTest.getLockContents();
        this.name = name;
        this.locksTest = locksTest;
        this.waitingProducer = locksTest.getWaitingProducer();
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        locksTest.getLogger().info(color + Thread.currentThread().getName() + " running");
        String value = null;
        while (value != "EOF") {
            locksTest.getLogger().debug(color + Thread.currentThread().getName() + " about to acquire lock ");
            if (lock.tryLock()) {
                try {
                    waitingProducer.await();
                    locksTest.getLogger().debug(color + Thread.currentThread().getName() + " hold count is " + lock.getHoldCount());
                    if (!contents.isEmpty()) {
                        Iterator<String> stringItr = contents.iterator();

                        while (stringItr.hasNext()) {
                            value = stringItr.next();
                            locksTest.getLogger().debug(color + "Consuming " + value);
                            stringItr.remove();

                            if ("EOF".equals(value)) {
                                locksTest.getLogger().debug(color + "Exiting..");
                                break;
                            }
                        }
                    }
                    lock.unlock();
                    locksTest.getLogger().debug(color + Thread.currentThread().getName() + " hold count after release is " + lock.getHoldCount());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    if(lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        }
    }
}