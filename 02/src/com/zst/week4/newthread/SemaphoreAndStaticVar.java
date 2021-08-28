package com.zst.week4.newthread;

import com.zst.week4.CallMe;

import java.util.concurrent.Semaphore;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：Semaphore阻塞
 * 数据共享方式：类静态变量
 */
public class SemaphoreAndStaticVar {
    static Semaphore semaphore = new Semaphore(0);
    static String ret;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            ret = CallMe.callMe();
            semaphore.release();
        }).start();

        semaphore.acquire();
        System.out.println(ret);
    }
}
