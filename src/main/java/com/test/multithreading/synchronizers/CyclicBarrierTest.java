package com.test.multithreading.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class CyclicBarrierTest {
    private static final int NUM_THREADS = 5;
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, () -> System.out.println("Barrier Action. All the threads are now terminated."));
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " running. Going to sleep");
                Thread.sleep((long) (10000 * Math.random()));
                System.out.println(Thread.currentThread().getName() + " Woke up now. Waiting at barrier");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        IntStream.range(0, NUM_THREADS).forEach(a -> new Thread(runnable).start());
    }
}
