package com.zst.week4.newthread;

import com.zst.week4.CallMe;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：CountDownLatch
 * 数据共享方式：类静态变量
 */
public class CountDownLatchAndStaticVar {
    static final CountDownLatch countDownLatch = new CountDownLatch(1);
    static String ret;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ret = CallMe.callMe();
            countDownLatch.countDown();
        }).start();

        System.out.println("Main Thread await, time=" + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now()));
        countDownLatch.await();
        System.out.println("Main Thread await complete, time=" + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now()));
        System.out.println(ret);
    }
}
