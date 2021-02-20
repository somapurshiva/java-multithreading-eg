package com.test.multithreading.suspendresume.runnables;

public class SuspendResumeOperator implements Runnable {
    private SuspendResumeRunnable obj;

    public SuspendResumeOperator(SuspendResumeRunnable obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        System.out.println("Started SuspendResumeOperator...");
        while (true) {
            System.out.println("SuspendResumeOperator requesting suspension...");
            obj.requestSuspend();
            System.out.println("SuspendResumeOperator Suspend requested...");

            this.goToSleep();

            System.out.println("SuspendResumeOperator requesting resumption...");
            obj.requestResume();
            System.out.println("SuspendResumeOperator Resume requested...");

            this.goToSleep();
        }
    }

    private void goToSleep() {
        System.out.println("Sleeping for Random time");
        try {
            Thread.sleep((long) (5000 * Math.random()));
        } catch (InterruptedException e) {
            System.out.println("SuspendResumeOperator failed with InterruptedException...");
            e.printStackTrace();
        }
        System.out.println("Woke up from sleep");
    }
}
