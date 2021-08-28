package com.zst.week4.threadpool;

import com.zst.week4.CallMe;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程创建方式：线程池
 * 线程通信方式：通过Future.isDone方法返回值判断是否执行结束，非阻塞的获取结果
 * 数据共享方式: Future
 */
public class NonBlockingCallable {
    static ExecutorService threadPool = Executors.newSingleThreadExecutor();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> ret = threadPool.submit(() -> {
            Thread.sleep((long) (Math.random() * 5000));
            return CallMe.callMe();
        });
        while (!ret.isDone()) {
            System.out.println("Main thread waiting callable done");
            Thread.sleep(1000L);
        }
        threadPool.shutdown();
        System.out.println(ret.get());
    }
}
