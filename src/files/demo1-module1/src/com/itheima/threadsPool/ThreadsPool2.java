package com.itheima.threadsPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadsPool2 {
    public static void main(String[] args) {
        // 自定义线程池
        /*
        参数1  核心线程数量,        不能小于0
        参数2 最大线程数,           不能<=0, 最大数量>=核心线程数量
        参数3 空闲线程最大存活时间   不能<=0
        参数4 时间单位              用TimeUnit指定
        参数5 任务队列              不能为null
        参数6 创建线程工厂          不能为null
        参数7 任务的拒绝策略         不能为null
         */


        //创建线程池
        ThreadPoolExecutor p1 = new ThreadPoolExecutor(
                3,
                6,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()        // 静态内部类,通过 new.
        );


        // 提交任务
//        p1.submit(new MyRun());
//        p1.submit(new MyRun());
//        p1.submit(new MyRun());
//        p1.submit(new MyRun());
//        p1.submit(new MyRun());
//        p1.submit(new MyRun());
//        p1.submit(new MyRun());
//        p1.submit(new MyRun());
//        p1.submit(new MyRun());
//        p1.submit(new MyRun());

        p1.shutdown();


        // 最大并行数  基本等于 cpu线程数
        int count = Runtime.getRuntime().availableProcessors();         //获取电脑线程数
        System.out.println(count);          // 12


        // 线程池多大合适?
        // cpu密集型运算: 最大并行数 + 1

        // I/O 密集型运算: 最大并行数 * 期望cpu利用率 * (cpu计算时间 + 等待时间) / cpu计算时间
        // 例: 12 * 100% * (1 + 1 ) / 1
        // cpu计算时间和等待时间可以用工具计算. 如thread dump

    }
}
