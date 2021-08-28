package com.zst.week4.newthread;

import com.zst.week4.CallMe;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：阻塞队列的阻塞入队列出队列
 * 数据共享方式：BlockingQueue
 */
public class BlockingQueueZ {
    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

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
