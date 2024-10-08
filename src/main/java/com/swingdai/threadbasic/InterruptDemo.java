package com.swingdai.threadbasic;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-09
 *
 * 线程终端demo 通过interrupt方法中断处于阻塞/等待/无限等待中的线程(I/O阻塞、synchronized锁无法中断)
 */
public class InterruptDemo {

    public static void main(String[] args) {

        System.out.println("main thread start");
        Thread thread1 = new Thread(new Thread1());
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("main thread got InterruptedException");
        }

        thread1.interrupt();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("main thread got InterruptedException");
        }
        System.out.println("main thread end");
    }
}

class Thread1 implements Runnable{
    @Override
    public void run() {

        System.out.println("Thread 1 start");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Thread 1 got InterruptedException");
            return;
        }

        System.out.println("Thread 1 end");
    }
}
