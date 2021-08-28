package com.zst.week4.newthread;

import com.zst.week4.CallMe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：semaphore非阻塞
 * 数据共享方式：类静态变量
 */
public class SemaphoreNonBlockingAndStaticVar {
    static Semaphore semaphore = new Semaphore(0);
    static String ret;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ret = CallMe.callMe();
            semaphore.release();
        }).start();

        while (!semaphore.tryAcquire(1000L, TimeUnit.MILLISECONDS)) {
            System.out.println("Main Thread waiting");
        }
        System.out.println(ret);
    }
}
