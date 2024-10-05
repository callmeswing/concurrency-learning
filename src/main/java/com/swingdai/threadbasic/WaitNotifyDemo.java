package com.swingdai.threadbasic;

import com.sun.deploy.util.StringUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-06
 *
 * wait/notify 使用案例
 * Object方法 会释放锁 优先notifyAll
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {
        TaskQueue taskQueue = new TaskQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();


        for (int i = 0; i < 5; i++) {
            executorService.execute(()->{
                while(true){
                    try {
                        String task = taskQueue.getTask();
                        System.out.println("execute task: " + task + " " + Thread.currentThread());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        executorService.execute(()->{
            for(int i = 1; i < 26; i++) {
                String task = "t-" + i;
                taskQueue.addTask(task);
                System.out.println("add task " + task);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        queue.add(s);
        // Second！
        // 使用notify()/notifyAll()唤醒wait中的线程
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while(queue.isEmpty()) {
            // First！
            // synchronized 对象锁 锁住了当前对象TaskQueue
            // 如果不能放掉锁 别的线程也无法访问addTask() 进而形成死锁
            // 需要注意异常处理
            this.wait();
        }
        return queue.remove();
    }
}
