package com.swingdai.threadbasic;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-03
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("First way: implements Runnable");
    }

    public static void main(String[] args) {
        MyRunnable instance = new MyRunnable();
        Thread thread = new Thread(instance);
        thread.start();
    }
}
