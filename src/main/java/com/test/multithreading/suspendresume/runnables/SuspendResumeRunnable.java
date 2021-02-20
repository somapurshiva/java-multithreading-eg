package com.test.multithreading.suspendresume.runnables;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SuspendResumeRunnable implements Runnable {
    private volatile boolean suspendRequested = false;
    private Lock suspendLock = new ReentrantLock();
    private Condition suspendCondition = suspendLock.newCondition();


    @Override
    public void run() {
        while (true) {
            System.out.println("SuspendResumeRunnable printed:::: "+Math.random());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("SuspendResumeRunnable failed with InterruptedException...");
                e.printStackTrace();
            }
            if (suspendRequested) {
                System.out.println("Suspend Requested. Trying to acquire lock");
                suspendLock.lock();
                System.out.println("Acquired lock");
                try {
                    System.out.println("Waiting for release");
                    while (suspendRequested) suspendCondition.await();
                    System.out.println("Released from suspension");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Releasing lock");
                    suspendLock.unlock();
                }
            }
        }
    }

    public void requestSuspend() {
        suspendRequested = true;
    }

    public void requestResume() {
        suspendRequested = false;
        System.out.println("Suspend Requested. Trying to acquire lock");
        suspendLock.lock();
        System.out.println("Acquired lock");
        try {
            System.out.println("Signalling Resumption");
            suspendCondition.signalAll();
        } finally {
            System.out.println("Releasing lock");
            suspendLock.unlock();
        }
    }
}
