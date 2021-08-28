package com.zst.week4.newthread;

import com.zst.week4.CallMe;

import java.util.concurrent.SynchronousQueue;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：SynchronousQueue的阻塞入队列出队列
 * 数据共享方式：SynchronousQueue
 */
public class SynchronousQueueZ {
    static SynchronousQueue<String> queue = new SynchronousQueue<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
                queue.put(CallMe.callMe());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(queue.take());
    }
}
