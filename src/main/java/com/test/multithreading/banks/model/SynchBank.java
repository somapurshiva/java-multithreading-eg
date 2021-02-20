package com.test.multithreading.banks.model;

import java.util.concurrent.locks.Condition;

public class SynchBank extends LockBank {
    private Condition sufficientFunds;

    public SynchBank(int n, double initialBalance) {
        super(n, initialBalance);
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) {
        System.out.println(Thread.currentThread() + "acquiring **** lock");
        bankLock.lock();
        System.out.println(Thread.currentThread() + "acquired #### lock");
        try {
            while (accounts[from] < amount) {
                System.out.println(Thread.currentThread() + "Waiting for notify %%%%");
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread() + "Entered transfer &&&& ");
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d%n", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            System.out.println(Thread.currentThread() + "Notifying All @@@@ ");
            sufficientFunds.signalAll();
//            sufficientFunds.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + "released $$$$ lock");
            bankLock.unlock();
        }
    }
}
