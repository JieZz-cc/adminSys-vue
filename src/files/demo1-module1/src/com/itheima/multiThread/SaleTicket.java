package com.itheima.multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket extends Thread{
    // static 表示多个对象共享ticket
    private static int ticket = 0;

    // 锁对象,一定要是唯一的,要加static
    static Object obj = new Object();

    // 一般可以用 类名.class (SaleTicket.class)

//    Lock 锁
    static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            // 1 同步方法
//            if (method()) break;

            // 2 同步代码块
//            synchronized (obj)
//            synchronized (SaleTicket.class) {
//                if (ticket < 30) {
//                    try {
//                        // 线程1、2、3都会在此休眠，有可能卖同一张票
//                        // 线程执行时，有随机性
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    ticket++;
//                    System.out.println(getName() + "正在卖第" + ticket + "张票");
//                } else {
//                    break;
//                }
//            }
            // 3 lock锁, 最好放在try catch里,finally一定会执行
            lock.lock();
            try {
                if (ticket == 30) {
                    break;
                } else {
                    Thread.sleep(100);
                    ticket++;
//                    String name = Thread.currentThread().getName(); === getName()
                    System.out.println(getName() + "正在卖第" + ticket + "张票");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        // synchronized (锁对象) {轮流执行的代码}
//       synchronized:  同步代码块, 把操作共享数据的代码锁起来
        // 锁默认打开,有个线程进去时,关闭,里面代码执行完后,线程出来,锁自动打开
    }


    // 同步方法:把synchronized关键字加到方法上
    // 格式: 修饰符 synchronized 返回类型 方法名(参数) {代码主体}
    // 同步方法的锁对象不能自己指定: 非静态的用this, 静态的用当前类的字节码文件对象(类名.class)
    private synchronized boolean method() {
        if (ticket < 30) {
            try {
                // 线程1、2、3都会在此休眠，有可能卖同一张票
                // 线程执行时，有随机性
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket++;
            System.out.println(getName() + "正在卖第" + ticket + "张票");
        } else {
            return true;
        }
        return false;
    }
}
