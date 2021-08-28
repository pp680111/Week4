package com.zst.week4.newthread;

import com.zst.week4.CallMe;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：wait/notify
 * 数据共享方式：类静态变量
 */
public class SynchronizedWaitAndStaticVar {
    public static final Object LOCK = new Object();
    static String ret;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (LOCK) {
                ret = CallMe.callMe();
                System.out.println("Sub thread notifyAll");
                LOCK.notifyAll();
            }
        }).start();
        synchronized (LOCK) {
            Thread.sleep(1000L);
            if (ret == null) {
                System.out.println("Main Thread wait");
                LOCK.wait();
            }
            System.out.println(ret);
        }
    }
}
