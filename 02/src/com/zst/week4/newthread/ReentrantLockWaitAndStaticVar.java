package com.zst.week4.newthread;

import com.zst.week4.CallMe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程创建方式：手动创建Thread对象
 * 线程通信方式：ReentrantLock的Condition.await和Condition.signalAll
 * 数据共享方式：类静态变量
 */
public class ReentrantLockWaitAndStaticVar {
    static final ReentrantLock lock = new ReentrantLock();
    static final Condition cond = lock.newCondition();
    static String ret;

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                ret = CallMe.callMe();
                System.out.println("Sub thread signalAll");
                cond.signalAll();
            } finally {
                lock.unlock();
            }
        }).start();

        lock.lock();
        try {
            if (ret == null) {
                System.out.println("Main thread await");
                cond.await();
            }
            System.out.println(ret);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
