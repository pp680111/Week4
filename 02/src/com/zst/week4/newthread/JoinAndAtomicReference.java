package com.zst.week4.newthread;

import com.zst.week4.CallMe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：join
 * 数据共享方式：AtomicReference
 */
public class JoinAndAtomicReference {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<String> ref = new AtomicReference<>();
        Thread t = new Thread(() -> {
            ref.compareAndSet(null, CallMe.callMe());
        });
        t.start();
        t.join();
        System.out.println(ref.get());
    }
}
