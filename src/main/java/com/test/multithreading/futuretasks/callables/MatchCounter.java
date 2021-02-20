package com.test.multithreading.futuretasks.callables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;

public class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;
    private Function<MatchCounter, Future<Integer>> function;
    private int count;

    /**
     * Constructs a MatchCounter
     * @param directory the directory in which to start the search
     * @param keyword the keyword to look for
     * */
    public MatchCounter(File directory, String keyword, Function<MatchCounter, Future<Integer>> function) {
        this.directory = directory;
        this.keyword = keyword;
        this.function = function;
    }

    /**
     * Searches a file for a given keyword
     * @param file the file to search
     * @return true if the keyword is contained in the file
     * */
    public boolean search(File file) {
        try {
            Scanner in = new Scanner(new FileInputStream(file));
            boolean found = false;
            while (!found && in.hasNextLine()) {
                String line = in.nextLine();
                if (line.contains(keyword)) found = true;
            }
            in.close();
            return found;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    @Override
    public Integer call() throws Exception {
        count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter counter = new MatchCounter(file, keyword, function);
                    results.add(function.apply(counter));
                } else {
                    if (search(file)) count++;
                }
            }
            for (Future<Integer> result : results) {
                try{
                    count += result.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }
}
