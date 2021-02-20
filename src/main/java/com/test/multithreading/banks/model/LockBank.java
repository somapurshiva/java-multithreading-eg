package com.test.multithreading.banks.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank extends Bank {
    protected Lock bankLock = new ReentrantLock();

    public LockBank(int n, double initialBalance) {
        super(n, initialBalance);
    }

    @Override
    public void transfer(int from, int to, double amount) {
        System.out.println(Thread.currentThread() + "acquiring **** lock");
        bankLock.lock();
        System.out.println(Thread.currentThread() + "acquired #### lock");
        try {
            if (accounts[from] < amount) return;
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        } finally {
            System.out.println(Thread.currentThread() + "released $$$$ lock");
            bankLock.unlock();
        }
    }
}
