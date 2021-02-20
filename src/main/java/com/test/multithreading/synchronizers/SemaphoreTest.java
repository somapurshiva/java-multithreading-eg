package com.test.multithreading.synchronizers;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore gate = new Semaphore(1);

        Runnable acquirer = () -> {
            while (true) {
                System.out.println("Acquirer: Trying to acquire");
                try {
                    gate.acquire();
                    System.out.println("Acquirer: Acquired permit");
                    System.out.println("Acquirer: Going to sleep");
                    Thread.sleep((long) (5000 * Math.random()));
                    System.out.println("Acquirer: Woke up now");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable releaser = () -> {
            while (true) {
                System.out.println("Releaser: releasing permit");
                try {
                    gate.release();
                    System.out.println("Releaser: released permit");
                    System.out.println("Releaser: Going to sleep");
                    Thread.sleep((long) (20000 * Math.random()));
                    System.out.println("Releaser: Woke up now");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(acquirer).start();
        new Thread(releaser).start();
    }
}
