package com.swingdai.lock;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-09-28
 *
 * 指令：
 * 1. java文件->class文件   javac xxx.java
 * 2. class文件解读     javap -verbose xxx.class
 */
public class SynchronizedDemo {

    Object object = new Object();

    public void method1(){
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + " RUN METHOD-1");
        }
        method2();
    }

    private static void method2(){
        System.out.println(Thread.currentThread().getName() + " RUN STATIC METHOD-2");
    }
}
