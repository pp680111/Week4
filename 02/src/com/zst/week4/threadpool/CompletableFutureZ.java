package com.zst.week4.threadpool;

import com.zst.week4.CallMe;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程创建方式：线程池
 * 线程通信方式：通过CompletableFuture.join方法阻塞的获取返回值
 * 数据共享方式: CompletableFuture
 */
public class CompletableFutureZ {
    static ExecutorService executor = Executors.newSingleThreadExecutor();
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(CallMe::callMe, executor);
        System.err.println(completableFuture.join());
        executor.shutdown();
    }
}
