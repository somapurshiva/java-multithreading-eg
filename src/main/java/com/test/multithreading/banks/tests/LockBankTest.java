package com.test.multithreading.banks.tests;

import com.test.multithreading.banks.model.Bank;
import com.test.multithreading.banks.model.LockBank;
import com.test.multithreading.banks.runnable.TransferRunnable;

public class LockBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        Bank b = new LockBank(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }
}
