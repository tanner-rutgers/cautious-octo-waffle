/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class ProducerConsumerBlocking {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }

    private static class Producer implements Runnable {

        BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 1; i < 100; i++) {
                try {
                    queue.put(i);
                    System.out.println("Producer put " + i + " on the queue");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.err.println("Producer was interrupted :(");
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                int i = 0;
                while (i <= 100) {
                    i = queue.take();
                    System.out.println("Consumer took " + i + " off the queue");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException ex) {
                System.err.println("Consumer was interrupted :(");
            }
        }
    }
}
