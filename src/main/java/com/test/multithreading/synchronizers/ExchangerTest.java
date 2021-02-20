package com.test.multithreading.synchronizers;

import java.util.concurrent.Exchanger;
import java.util.stream.IntStream;

public class ExchangerTest {
    private static final int NUM_THREADS = 2;
    public static void main(String[] args) {
        Exchanger<Double> exchanger = new Exchanger<>();

        Runnable runnable = () -> {
            while (true) {
                double sendVal = Math.random();
                double receiveVal = 0;
                System.out.println(Thread.currentThread().getName() + " thread sending value = "+sendVal);
                try {
                    receiveVal = exchanger.exchange(sendVal);
                    System.out.println(Thread.currentThread().getName() + " thread received value = "+receiveVal);

                    System.out.println(Thread.currentThread().getName() + " going to sleep");
                    Thread.sleep((long) (10000 * Math.random()));
                    System.out.println(Thread.currentThread().getName() + " woke up now");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        IntStream.range(0, NUM_THREADS).forEach(a -> new Thread(runnable).start());
    }
}
