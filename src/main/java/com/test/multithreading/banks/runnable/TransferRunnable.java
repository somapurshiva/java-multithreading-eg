package com.test.multithreading.banks.runnable;

import com.test.multithreading.banks.model.Bank;

public class TransferRunnable implements Runnable {
    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private int DELAY = 5000;

    public TransferRunnable(Bank b, int from, double max) {
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
//                bank.transfer(toAccount, fromAccount, amount);
                Thread.sleep((long) (DELAY * Math.random()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
