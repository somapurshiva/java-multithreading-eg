package com.test.multithreading.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchTest {
    private static final int NUM_THREADS = 5;
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(NUM_THREADS);
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " running. Going to sleep");
                Thread.sleep((long) (10000 * Math.random()));
                System.out.println(Thread.currentThread().getName() + " Woke up now. Waiting at latch");
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable latchDecrementer = () -> {
          int count = NUM_THREADS;
          while (count != 0) {
              System.out.println("Latch decrementer going into sleep");
              try {
                  Thread.sleep((long) (5000 * Math.random()));
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              count--;
              latch.countDown();
              System.out.println("Latch decrementer counted down");
          }
            System.out.println("Latch decrementer game over");
        };

        IntStream.range(0, NUM_THREADS).forEach(a -> new Thread(runnable).start());
        new Thread(latchDecrementer).start();
    }
}
