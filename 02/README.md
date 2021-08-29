>  思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程?

从题目中可以看出，可以按照线程启动方式，线程共享数据方式和线程间通信方式三个维度来构造出答案。

线程启动的方式有以下几种:

* 手动创建一个线程对象，调用其start方法
* 提交任务到线程池，让线程池中的线程去执行

线程间共享数据的方式有以下几种：

* 通过对象成员变量或者static变量共享
* 作为集合的元素共享
* 线程执行的结果作为Future接口类方法调用的返回值返回

线程间通信的方式有以下几种：

* 主线程调用join阻塞自己直到子线程执行结束
* 主线程调用wait|await|park方法等待子线程执行完成调用notify|notifyAll|signal|signalAll|unpark唤醒主线程
* 阻塞队列实现的阻塞的生产者-消费者模式
* 基于AQS实现的并发控制工具，如Semaphore，CountDownLatch等
* Future接口类实现的等待线程执行结束

对上面三个维度中的各种方式进行排列组合，去掉一些重复度较高的方式，得到以下方式：

* JoinAndStaticVar，手动创建线程对象调用start方法启动线程，静态变量共享方法返回值，通过join方法进行通信
* JoinAndAtomicReference，手动创建线程对象调用start方法启动线程，通过成员变量共享方法返回值，通过join方法进行通信
* SynchronizedWaitAndStaticVar，手动创建线程对象调用start方法启动线程，静态变量共享方法返回值，通过wait和notifyAll进行通信
* ReentrantLockWaitAndStaticVar，手动创建线程对象调用start方法启动线程，静态变量共享方法返回值，通过Semaphore的Condition类的await和signalAll进行通信
* SemaphoreAndStaticVar，手动创建线程对象调用start方法启动线程，静态变量共享方法返回值，通过Semaphore进行线程通信
* SemaphoreNonBlockingAndStaticVar，手动创建线程对象调用start方法启动线程，静态变量共享方法返回值，通过Semaphore进行非阻塞的线程通信。
* CountDownLatchAndStaticVar，手动创建线程对象调用start方法启动线程，静态变量共享方法返回值，通过CountDownLatch进行阻塞的线程通信。
* BlockingQueueZ，手动创建线程对象调用start方法启动线程，通过集合共享方法返回值，阻塞队列实现线程通信。
* SynchronousQueueZ，手动创建线程对象调用start方法启动线程，通过集合共享方法返回值，阻塞队列实现线程通信。
* LockSupportAndStaticVar， 手动创建线程对象调用start方法启动线程，静态变量共享方法返回值，通过LockSupport进行阻塞的线程通信。
* BlockingCallable，提交任务到线程池的方式启动线程，通过Future接口类方法调用获取方法返回值，通过Future接口类实现的等待线程执行结束
* NonBlockingCallable，提交任务到线程池的方式启动线程，通过Future接口类方法调用获取方法返回值通过Future接口类的方法获取线程执行状态，自旋式的等待线程执行结束
* CompletableFutureZ，提交任务到线程池的方式启动线程，通过Future接口类方法调用获取方法返回值，通过Future接口类实现的等待线程执行结束。