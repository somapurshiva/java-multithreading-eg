package com.test.multithreading.blockingqueue.runnables;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * This task searches files for a given keyword.
 * */
public class SearchTask implements Runnable {
    private BlockingQueue<File> queue;
    private String keyword;

    /**
     * Constructs a SearchTask
     * @param queue the queue from which to take files
     * @param keyword the keyword to look for
     * */
    public SearchTask(BlockingQueue<File> queue, String keyword) {
        this.queue = queue;
        this.keyword = keyword;
    }

    public void search(File file) throws IOException {
        Scanner in = new Scanner(new FileInputStream(file));
        int lineNumber = 0;
        while (in.hasNextLine()) {
            lineNumber++;
            String line = in.nextLine();
            if (line.contains(keyword))
                System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
        }
        in.close();
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            while (!done) {
                File file = queue.take();
                if (file == FileEnumerationTask.DUMMY) {
                    queue.put(file);
                    done = true;
                } else
                    search(file);
            }
        } catch (InterruptedException | IOException e) {
            System.out.println("InterruptedException/IOException thrown in the SearchTask");
            e.printStackTrace();
        }
    }
}
