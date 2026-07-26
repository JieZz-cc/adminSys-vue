package com.itheima.multiThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建多线程有3种方式

        // 方法1、继承thread类
        /*自己定义一个类并继承thread
        重写run方法
        创建子类对象并启动线程 */

        MyThread mt1 = new MyThread();
        MyThread mt2 = new MyThread();
        MyThread mt3 = new MyThread();
        mt1.setName("线程1");
        mt2.setName("线程2");
        mt3.setName("线程3");

        // 设置为守护线程， 其他非守护线程结束后，守护线程跟着陆续结束，时间不定
        mt2.setDaemon(true);

        // 启动线程
        mt1.start();

        mt1.join();  // 将mt1线程插入到当前线程之前，当前在main线程， 线程1执行完后执行下一个线程

        mt2.start();

        mt2.join();  // 将mt1线程插入到当前线程之前，当前在main线程（主线程）

        mt3.start();
        System.out.println(mt1.getPriority());
        // 程序一会打印1，一会打印2




        // 方式2：实现runnable接口
        /*
        先定义一个类实现runnable接口
        重写run方法
        创建实现类对象
        创建thread类对象并开启线程
         */
        // 多线程要执行的代码
        MyRun r1 = new MyRun();
        // 创建线程对象
        Thread t1 = new Thread(r1, "11");
        Thread t2 = new Thread(r1, "22");

//        t1.setName("线程1");
//        t2.setName("线程2");
        // 数字越小，优先级越高，代表抢占到cpu资源的概率愈大，不代表一定先执行
        t1.setPriority(6);
        t2.setPriority(2);

        System.out.println(t1.getPriority());


        // 开启
        t1.start();
        t2.start();




        // 方式3：实现callable接口
        /*
        1 创建一个类MyCallable实现callable接口
        2 重写call方法，（有返回值，表示多线程运行的结果）
        3 MyCallable类实例化（多线程要执行的任务）
        4 实例化FutureTask对象（管理多线程运行的结果）
        5 实例化thread，并启动（线程）
         */
        MyCallable mc = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(mc);
        Thread tt1 = new Thread(ft);
        tt1.start();
        System.out.println(ft.get());       // 线程返回的结果









        // 线程的一些方法
        // getName()  setName()    sleep()
        // setPriority(int num)   设置进程的优先级，1-10之间，越小优先级越高，默认5, 虚拟机默认的main线程也是5
        // final int getPriority  获取进程优先级
        // final void setDaemon(boolean on)  设置为守护线程
        // public static void yield()       出让线程/礼让线程
        // public final void join()         插队线程


    }
}
