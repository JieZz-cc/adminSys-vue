package com.itheima.multiThread;

public class WaitNotify {
    public static void main(String[] args) {
        // 等待唤醒机制(生产者和消费者)

        // 厨师和吃货两个进程轮流执行, wait和notify
        Cook c = new Cook();
        Foodie f = new Foodie();

        c.setName("厨师");
        f.setName("吃货");

        c.start();
        f.start();
    }
}
