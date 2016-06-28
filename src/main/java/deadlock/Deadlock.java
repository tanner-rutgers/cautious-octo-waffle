/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package deadlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class Deadlock {

    List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    Iterator<Integer> iter = list.iterator();
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Future<Integer> future = executorService.submit(new Callable<Integer>() {
        @Override
        public Integer call() {
            return 10;
        }
    });


    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        Deadlock deadlock = new Deadlock();
        deadlock.run();


    }

    private void run() throws Exception {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("blah");
            }
        });

        future.get();
        future.cancel(true);
        future.isDone();

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        Future<Integer> future1 = executorService.submit(new MyCallable(list, 1));
//        Future<Integer> future2 = executorService.submit(new MyCallable(list, 2));
//
//        System.out.println(future1.get() + " finished");
//        System.out.println(future2.get() + " finished");
//        for (Integer i : list) {
//            System.out.print(i + ",");
//        }

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                getLocks(lock1, lock2);
                System.out.println("t1 got all locks");
                System.out.println("t1 releasing locks");
                lock1.unlock();
                lock2.unlock();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                getLocks(lock2, lock1);
                System.out.println("t2 got all locks");
                System.out.println("t2 releasing locks");
                lock1.unlock();
                lock2.unlock();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Finished");
    }

    public void getLocks(Lock... locks) {
        boolean gotAllLocks = true;
        while (true) {
            gotAllLocks = true;
            for (Lock lock : locks) {
                gotAllLocks = gotAllLocks && lock.tryLock();
            }
            if (!gotAllLocks) {
                for (Lock lock : locks) {
                    if (Thread.holdsLock(lock)) {
                        lock.unlock();
                    }
                }
            } else {
                return;
            }
        }
    }

    class MyCallable implements Callable<Integer> {

        List<Integer> list;
        Integer id;

        public MyCallable(List<Integer> list, Integer id) {
            this.list = list;
            this.id = id;
        }

        @Override
        public Integer call() {
            for (int i = 0; i < 100; i++) {
                if (list.size() <= 0) {
                    list.add(0);
                } else {
                    Integer last = list.get(list.size() - 1);
                    list.add(last + 1);
                }
            }
            return id;
        }
    }
}
