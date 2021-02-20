package com.test.multithreading.synchronizers;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<Double> queue = new SynchronousQueue<>();

        Runnable sender = () -> {
            while (true) {
                double sendVal = Math.random();
                System.out.println(Thread.currentThread().getName() + " thread sending value = "+sendVal);
                try {
                    queue.put(sendVal);
                    System.out.println(Thread.currentThread().getName() + " going to sleep");
                    Thread.sleep((long) (10000 * Math.random()));
                    System.out.println(Thread.currentThread().getName() + " woke up now");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable receiver = () -> {
            while (true) {
                double receiveVal;
                try {
                    receiveVal = queue.take();
                    System.out.println(Thread.currentThread().getName() + " thread received value = "+receiveVal);

                    System.out.println(Thread.currentThread().getName() + " going to sleep");
                    Thread.sleep((long) (10000 * Math.random()));
                    System.out.println(Thread.currentThread().getName() + " woke up now");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(sender).start();
        new Thread(receiver).start();
    }
}
