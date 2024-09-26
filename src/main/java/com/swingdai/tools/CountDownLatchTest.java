package com.swingdai.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-09-27
 */
public class CountDownLatchTest {

    private int number = 0;

    public void add(){
        number++;
    }

    public int get(){
        return number;
    }


    public static void main(String[] args) throws InterruptedException {

        final int threadSize = 1000;
        CountDownLatchTest test = new CountDownLatchTest();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0; i<threadSize; i++){
            executorService.execute(() -> {
                test.add();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println(test.get());
    }

}
