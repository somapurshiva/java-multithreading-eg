package com.test.multithreading.suspendresume.test;

import com.test.multithreading.suspendresume.runnables.SuspendResumeOperator;
import com.test.multithreading.suspendresume.runnables.SuspendResumeRunnable;

public class SuspendResumeTest {
    public static void main(String[] args) {
        // First create the Suspend resume runnable and start it
        SuspendResumeRunnable obj = new SuspendResumeRunnable();
        Thread t1 = new Thread(obj);
        t1.start();

        // Then create the operator and start it
        SuspendResumeOperator operator = new SuspendResumeOperator(obj);
        Thread t2 = new Thread(operator);
        t2.start();
    }
}
