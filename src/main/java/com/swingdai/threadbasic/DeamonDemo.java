package com.swingdai.threadbasic;

import javax.swing.plaf.TableHeaderUI;

/**
 * @author SwingDai
 * @version 1.0
 * @date 2024-10-08
 *
 * 守护线程demo 所有非守护线程死亡后，JVM退出，无视正在运行的守护线程。
 */
public class DeamonDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(()->{
           while(true){
           }
        });

        // to activate / deactivate this following line to see what's different
        thread.setDaemon(true);
        thread.start();
        

        for (int i=5; i>0; i--){
            System.out.println("count down: " + i + "second");
            Thread.sleep(1000);
        }
        System.out.println("main thread end!");
    }
}


