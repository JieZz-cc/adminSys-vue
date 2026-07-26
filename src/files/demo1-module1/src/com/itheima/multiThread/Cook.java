package com.itheima.multiThread;

public class Cook extends Thread{
    // 厨师-生产者
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {
                    break;
                } else {
                    if (Desk.foodFlag == 1) {
                        try {
                            Desk.lock.wait();    // 等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("厨师做了一份");
                        Desk.foodFlag = 1;
                        Desk.lock.notifyAll();      // 唤醒
                    }
                }
            }
        }
    }
}
