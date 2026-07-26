package com.itheima.multiThread;

public class Foodie extends Thread{
    // 吃货-消费者
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {
                    break;
                } else {
                    if(Desk.foodFlag == 0) {
                        // 没有食物等待
                        try {
                            Desk.lock.wait();       // 等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Desk.count--;
                        System.out.println("吃货在吃东西,还能再吃" + Desk.count + "份");
                        Desk.lock.notifyAll();      // 唤醒
                        Desk.foodFlag = 0;
                    }
                }
            }
        }
    }
}
