package com.itheima.threadsPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsPool {
    public static void main(String[] args) throws InterruptedException {
        // 线程池
//        MyRun r1 = new MyRun();
        // 1 获取线程池对象
//        ExecutorService pool1 = Executors.newCachedThreadPool();       // 没有线程数上线的池子(实际有,21E)
        ExecutorService pool1 = Executors.newFixedThreadPool(3);       // 有线程数上线的池子, 上限3, 此时只有3个线程

        // 提交任务
        pool1.submit(new MyRun());
//        Thread.sleep(1000);     // 线程服用,每次用的都是线程1  pool-1-thread-1

        pool1.submit(new MyTest());
//        Thread.sleep(1000);

//        pool1.submit(new MyRun());
//        Thread.sleep(1000);

//        pool1.submit(new MyRun());
//        Thread.sleep(1000);

//        pool1.submit(new MyRun());

        // 关闭线程池
//        pool1.shutdown();
    }
}
