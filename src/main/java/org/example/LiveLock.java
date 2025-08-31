package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class LiveLock implements Runnable {
    private static final String MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK = "Поток '%s' пытается захватить замок '%s'\n";
    private static final String MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK = "Поток '%s' успешно захватитил замок '%s'\n";
    private static final String MESSAGE_TEMPLATE_RELEASE_LOCK = "Поток '%s' освободил замок '%s'\n";
    private static final String NAME_FIRST_LOCK = "первый замок";
    private static final String NAME_SECOND_LOCK = "второй замок";

    private final Lock firstLock;
    private final Lock secondLock;

    public LiveLock(Lock firstLock, Lock secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void run() {
        final String currentThread = Thread.currentThread().getName();
        System.out.printf(MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK, currentThread, NAME_FIRST_LOCK);
        this.firstLock.lock();
        try {
            System.out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThread, NAME_FIRST_LOCK);
            TimeUnit.MILLISECONDS.sleep(50);
            while (!this.tryAcquireSecondLock()) {
                TimeUnit.MILLISECONDS.sleep(50);
                this.firstLock.unlock();
                System.out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThread, NAME_FIRST_LOCK);
                TimeUnit.MILLISECONDS.sleep(50);
                System.out.printf(MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK,currentThread,NAME_FIRST_LOCK);
                this.firstLock.lock();
                System.out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK,currentThread,NAME_FIRST_LOCK);
                TimeUnit.MILLISECONDS.sleep(50);
            }
            try {
                System.out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThread, NAME_SECOND_LOCK);
            } finally {
                this.secondLock.unlock();
                System.out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThread, NAME_SECOND_LOCK);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            this.firstLock.unlock();
            System.out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThread, NAME_FIRST_LOCK);
        }
    }

    private boolean tryAcquireSecondLock() {
        final String currentThread = Thread.currentThread().getName();
        System.out.printf(MESSAGE_TEMPLATE_TRY_ACQUIRE_LOCK, currentThread, NAME_SECOND_LOCK);
        return this.secondLock.tryLock();
    }
}
