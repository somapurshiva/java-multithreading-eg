package com.test.multithreading.futuretasks.tests;

import com.test.multithreading.futuretasks.callables.MatchCounter;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
        String directory = in.nextLine();
        System.out.print("Enter keyword (e.g. volatile): ");
        String keyword = in.nextLine();

        Function<MatchCounter, Future<Integer>> function = matchCounter -> {
            FutureTask<Integer> task = new FutureTask<>(matchCounter);
            new Thread(task).start();
            return task;
        };

        MatchCounter counter = new MatchCounter(new File(directory), keyword, function);
        Future<Integer> task = function.apply(counter);

        System.out.println(task.get() + " matching files");
    }
}
