package com.zst.week4.newthread;

import com.zst.week4.CallMe;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：join
 * 数据共享方式：类静态变量
 */
public class JoinAndStaticVar {
    static String ret;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            ret = CallMe.callMe();
        });
        t.start();
        t.join();
        System.out.println(ret);
    }
}
