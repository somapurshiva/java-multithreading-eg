package com.test.multithreading.banks.model;

public class SynchBank2 extends Bank {
    public SynchBank2(int n, double initialBalance) {
        super(n, initialBalance);
    }

    @Override
    public synchronized void transfer(int from, int to, double amount) {
        System.out.println(Thread.currentThread() + "acquired #### lock");
        try {
            while (accounts[from] < amount) {
                System.out.println(Thread.currentThread() + "Waiting for notify %%%%");
                wait();
            }
            System.out.println(Thread.currentThread() + "Entered transfer &&&& ");
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d%n", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            System.out.println(Thread.currentThread() + "Notifying All @@@@ ");
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized double getTotalBalance() {
        return super.getTotalBalance();
    }
}
