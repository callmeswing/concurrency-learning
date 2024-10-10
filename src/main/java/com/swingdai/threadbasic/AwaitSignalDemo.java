package com.swingdai.threadbasic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-10
 *
 * 通过Lock获取Condition
 */
public class AwaitSignalDemo {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void before(){
        lock.lock();

        try {
            System.out.println("before");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void after(){
        lock.lock();

        try {
            condition.await();
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AwaitSignalDemo awaitSignalDemo = new AwaitSignalDemo();

        executorService.execute(()->{awaitSignalDemo.after();});
        Thread.sleep(1000);
        executorService.execute(()->{awaitSignalDemo.before();});
    }


}
