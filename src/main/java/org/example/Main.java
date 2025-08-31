package org.example;

public class Main {
    public static void main(String[] args) {

// DeadLock
//        final Lock firstLock = new ReentrantLock();
//        final Lock secondLock = new ReentrantLock();
//        final Thread firstThread = new Thread(new DeadLock(firstLock, secondLock),"№ 1");
//        final Thread secondThread = new Thread(new DeadLock(secondLock, firstLock),"№ 2");
//        firstThread.start();
//        secondThread.start();

// LiveLock
//        final Lock firstLock = new ReentrantLock();
//        final Lock secondLock = new ReentrantLock();
//        final Thread firstThread = new Thread(new LiveLock(firstLock, secondLock),"№ 1");
//        final Thread secondThread = new Thread(new LiveLock(secondLock, firstLock),"№ 2");
//        firstThread.start();
//        secondThread.start();

// Printer 1-2-1-2...
//        Printer printer = new Printer();
//        Thread firstLock = new Thread(() -> printer.printOne(),"№ 1");
//        Thread secondLock = new Thread(() -> printer.printTwo(),"№ 2");
//        firstLock.start();
//        secondLock.start();

    }
}