package com.swingdai.threadbasic;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-09
 *
 * interrupt()会设置线程中断标记
 * 无限循环的线程 无法通过interrupt()直接中断 但是可以通过interrupted()获取中断标记自行判断
 */
public class InterruptedDemo {
    public static void main(String[] args) {
        Thread thread2 = new Thread(new Thread2());
        thread2.start();
        thread2.interrupt();

    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread 2 start");

        while(!Thread.interrupted()){
        }

        System.out.println("Thread 2 end");
    }
}
