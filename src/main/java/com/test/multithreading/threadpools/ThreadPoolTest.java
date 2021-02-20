package com.test.multithreading.threadpools;

import com.test.multithreading.futuretasks.callables.MatchCounter;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
        String directory = in.nextLine();
        System.out.print("Enter keyword (e.g. volatile): ");
        String keyword = in.nextLine();

//        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool = Executors.newFixedThreadPool(20);

        MatchCounter counter = new MatchCounter(new File(directory), keyword, pool::submit);
        Future<Integer> task = pool.submit(counter);

        System.out.println(task.get() + " matching files.");

        pool.shutdown();

        int largestPoolSize = ((ThreadPoolExecutor)pool).getLargestPoolSize();
        System.out.println("Largest pool size = "+ largestPoolSize);
    }
}
