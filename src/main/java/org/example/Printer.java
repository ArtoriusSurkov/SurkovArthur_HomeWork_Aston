package org.example;

public class Printer {

    private final Object lock = new Object();
    private boolean turn = true;

    public void printOne() {
        while (true) {
            synchronized (lock) {
                while (!turn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("1");
                turn = false;
                lock.notify();
            }
        }
    }

    public void printTwo() {
        while (true) {
            synchronized (lock) {
                while (turn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("2");
                turn = true;
                lock.notify();
            }
        }
    }
}




