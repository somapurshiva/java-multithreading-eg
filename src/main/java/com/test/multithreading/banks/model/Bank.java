package com.test.multithreading.banks.model;

import java.util.Arrays;

public class Bank {
    protected final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < n; i++) {
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) return;
        System.out.print(Thread.currentThread() + " - " + Thread.currentThread().getThreadGroup().getName());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
    }

    public double getTotalBalance() {
        return Arrays.stream(accounts).sum();
    }

    public int size() {
        return accounts.length;
    }
}
