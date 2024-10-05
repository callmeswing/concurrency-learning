package com.swingdai.keyword;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-09-28
 *
 * count++ 是一个三步骤复合操作(1.读取count值 2.对count+1 3.写回内存) 不具有原子性
 * 即便加上volatile关键字 也不能保障原子性
 * volatile可以保障可见性 以及单次读写的原子性
 */
public class VolatileTest {

    volatile int count;

    public void addCount(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileTest volatileTest  = new VolatileTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        int threadNumber = 1000;
        for(int i=0; i<threadNumber; i++){
            executorService.execute(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                volatileTest.addCount();
            });
        }

        Thread.sleep(10000);
        executorService.shutdown();
        System.out.println(volatileTest.count);
    }
}
