package com.itheima.multiThread;

public class Desk {
    // 控制生产者和消费者的执行

    // 是否有食物 0无
    public static int foodFlag = 0;
    // 总数量
    public static int count = 10;
    // 锁对象
    public static Object lock = new Object();
}
