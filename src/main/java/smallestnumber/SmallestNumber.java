/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package smallestnumber;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class SmallestNumber {

    public static void main(String[] args) {
        SmallestNumber smallestNumber = new SmallestNumber();
        smallestNumber.run();
    }

    public void run() {
        Random random = new Random();
        int[] numbers = new int[10000000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(1000);
        }

        Long t1 = System.currentTimeMillis();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        Long t2 = System.currentTimeMillis();
        System.out.println("iterative found min " + min + " in " + (t2 - t1) + "ms");

        Long t3 = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(10);
        min = pool.invoke(new RecursiveSmallest(numbers, 0, numbers.length - 1));
        Long t4 = System.currentTimeMillis();
        System.out.println("forkjoin found min " + min + " in " + (t4 - t3) + "ms");
        pool.shutdown();
    }

    public class RecursiveSmallest extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 100;

        int[] arr;
        int start;
        int end;

        public RecursiveSmallest(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int length = end - start;
            if (length < THRESHOLD) {
                return iterativeMin();
            }
            RecursiveSmallest s1 = new RecursiveSmallest(arr, start, start + length / 2);
            RecursiveSmallest s2 = new RecursiveSmallest(arr, start + length / 2, end);
            s1.fork();
            int result2 = s2.compute();
            int result1 = s1.join();
            return Math.min(result1, result2);
        }

        private Integer iterativeMin() {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                }
            }
            return min;
        }
    }
}
