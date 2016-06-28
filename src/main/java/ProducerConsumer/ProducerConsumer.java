/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }

    private static class Producer implements Runnable {

        Queue<Integer> queue;

        public Producer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 1; i < 50; i++) {
                try {
                    synchronized (queue) {
                        while (queue.size() >= 5) {
                            System.out.println("Queue is full, waiting...");
                            queue.wait();
                        }
                        queue.add(i);
                        System.out.println("Producer put " + i + " on the queue");
                        queue.notify();
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.err.println("Producer was interrupted :(");
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        Queue<Integer> queue;

        public Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    synchronized (queue) {
                        while (queue.size() < 3) {
                            System.out.println("Queue does not have 3 elements, waiting...");
                            queue.wait();
                        }
                        for (int i = 0; i < 3; i++) {
                            int num = queue.poll();
                            System.out.println("Consumer took " + num + " off the queue");
                            queue.notify();
                        }
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    System.err.println("Consumer was interrupted :(");
                }
            }
        }
    }
}
