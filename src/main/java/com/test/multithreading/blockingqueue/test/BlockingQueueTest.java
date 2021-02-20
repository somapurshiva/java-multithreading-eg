package com.test.multithreading.blockingqueue.test;

import com.test.multithreading.blockingqueue.runnables.FileEnumerationTask;
import com.test.multithreading.blockingqueue.runnables.SearchTask;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class BlockingQueueTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
        String directory = in.nextLine();
        System.out.print("Enter keyword (e.g. volatile): ");
        String keyword = in.nextLine();

        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_THREADS = 100;

        BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

        FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
        new Thread(enumerator).start();
        IntStream.range(0, SEARCH_THREADS).forEach((a) -> new Thread(new SearchTask(queue, keyword)).start());
    }
}
