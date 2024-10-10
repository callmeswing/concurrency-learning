package com.swingdai.threadbasic;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-09
 *
 * join()方法 挂起当前线程，等待被调用线程执行结束后加入执行
 */
public class JoinDemo {

    public static void main(String[] args) {
        Thread thread3 = new Thread(new Thread3());
        thread3.start();

        try {
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("join got InterruptedException");
        }

        System.out.println("main thread end...");

    }
}

class Thread3 implements Runnable{
    @Override
    public void run() {

        System.out.println("Thread3 start");
        try {
            Thread.sleep(2000);
            throw new InterruptedException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread3 end");
    }
}

