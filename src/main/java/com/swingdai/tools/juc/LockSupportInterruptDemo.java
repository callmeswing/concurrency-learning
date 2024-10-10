package com.swingdai.tools.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-11
 *
 * interrupt() 作用同 unpark()  详见park()阻塞取消条件
 */
public class LockSupportInterruptDemo {

    public static void main(String[] args) {
        Thread3 runnable3 = new Thread3(Thread.currentThread());
        Thread thread3 = new Thread(runnable3);
        thread3.start();

        System.out.println("before park");
        LockSupport.park();
        System.out.println("after park");
    }
}

class Thread3 implements Runnable {
    private Thread targetThread;

    public Thread3(Thread thread) {
        this.targetThread = thread;
    }

    public void run() {
        System.out.println("before interrupt");
        try {
            // 休眠
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断主线程
        targetThread.interrupt();
        System.out.println("after interrupt");
    }
}

