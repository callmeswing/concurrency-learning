package com.swingdai.threadbasic;

import java.sql.SQLOutput;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-03
 */
public class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "Second way: implements Callable";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable instance = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(instance);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }
}
