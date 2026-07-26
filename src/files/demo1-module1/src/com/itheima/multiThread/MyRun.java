package com.itheima.multiThread;

public class MyRun implements Runnable{
    @Override
    public void run() {
        // 获取当前线程对象
        Thread t = Thread.currentThread();
        for (int s = 0; s < 20; s++) {
            System.out.println(t.getName() + "开始");
        }
    }
}
