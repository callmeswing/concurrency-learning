package com.swingdai.threadbasic;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-03
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("Third way: extends Thread");
    }

    public static void main(String[] args) {
        MyThread instance = new MyThread();
        instance.start();
    }
}
