package com.itheima.multiThreadTest;

import java.util.Random;

public class MyThread1 extends Thread{
    // 抢红包,100快分3个,5个人抢
    static int money = 100;
    static int count = 3;
    static final int MIN = 1;

    @Override
    public void run() {
        synchronized (MyThread1.class) {
            if (count == 0) {
                System.out.println(getName() + "没抢到红包");
            } else {
                int temp;        // 抢到的金额
                if (count == 1) {
                    temp = money;
                } else {
                    Random r = new Random();
                    int bounds = money - (count - 1) * MIN;
                    temp = r.nextInt(bounds);
                    temp = temp < MIN ? MIN : temp;
                }
                money = money - temp;
                count--;
                System.out.println(getName() + "抢到" + temp + "元");
            }
        }
    }
}
