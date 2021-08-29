package com.zst.week4.newthread;

import com.zst.week4.CallMe;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：LockSupport的park和unpark方法
 * 数据共享方式：类静态变量
 */
public class LockSupportAndStaticVar {
    static String ret;

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        new Thread(() -> {
            ret = CallMe.callMe();
            LockSupport.unpark(t);
        }).start();
        LockSupport.park();
        System.out.println(ret);
    }
}
