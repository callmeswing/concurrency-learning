package com.swingdai.tools.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-11
 *
 * LockSupport 提供park()/unpark()方法 较wait/notify更为灵活(顺序颠倒不会阻塞)
 * 注意: 若没有线程执行unpark() 仅进行了park() park线程依然会阻塞
 */
public class LockSupportDemo {


    public static void main(String[] args) throws InterruptedException {
        LockSupportDemo lockSupportDemo = new LockSupportDemo();

        // 先park再unpark
//        lockSupportDemo.testParkUnpark();
        // 先unpark再park
        lockSupportDemo.testUnparkPark();

    }

    public void testParkUnpark(){
        Thread1 runnable1 = new Thread1(Thread.currentThread());
        Thread thread1 = new Thread(runnable1);

        thread1.start();
        System.out.println("before park");
        LockSupport.park("swing dai");
        System.out.println("after park");
    }

    public void testUnparkPark() throws InterruptedException {
        Thread2 runnable2 = new Thread2(Thread.currentThread());
        Thread thread2 = new Thread(runnable2);
        thread2.start();

        Thread.sleep(2000);
        System.out.println("before park");
        System.out.println("main thread blocker " + LockSupport.getBlocker(Thread.currentThread()));
        LockSupport.park("swing dai");
        System.out.println("after park");
        System.out.println("main thread blocker " + LockSupport.getBlocker(Thread.currentThread()));

    }

}

class Thread1 implements Runnable{

    private Thread targetThread;

    public Thread1(Thread targetThread) {
        this.targetThread = targetThread;
    }

    @Override
    public void run() {

        System.out.println("before unpark");
        // 休眠 确保setBlocker
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 查看blocker
        System.out.println("main thread blocker " + LockSupport.getBlocker(targetThread));

        // unpark 释放许可
        LockSupport.unpark(targetThread);

        // 休眠 确保park()执行第二次setBlocker(t, null)
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after unpark");
        // 再次查看blocker
        System.out.println("main thread blocker " + LockSupport.getBlocker(targetThread));

    }
}

class Thread2 implements Runnable{

    private Thread targetThread;

    public Thread2(Thread targetThread) {
        this.targetThread = targetThread;
    }
    @Override
    public void run() {
        System.out.println("before unpark");
        LockSupport.unpark(targetThread);
        System.out.println("after unpark");
    }
}
