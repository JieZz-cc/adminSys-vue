package com.itheima.multiThread;

public class MyThread extends Thread{
    @Override
    public void run() {
        // 线程要执行的代码
        for (int i = 0; i < 20; i++) {
//            try {
//                Thread.sleep(2000); // 休眠
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(getName() + "开始");     // getName()获取线程名
            // 出让当前cpu的执行权，后续重新抢
            Thread.yield();
        }
    }
}
