package com.test.multithreading.banks.tests;

import com.test.multithreading.banks.model.Bank;
import com.test.multithreading.banks.model.SynchBank2;
import com.test.multithreading.banks.runnable.TransferRunnable;

public class SynchBankTest2 {
    public static final int NACCOUNTS = 5;
    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        Bank b = new SynchBank2(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }
}
