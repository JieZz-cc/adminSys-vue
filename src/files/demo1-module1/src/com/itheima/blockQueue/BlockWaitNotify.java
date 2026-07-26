package com.itheima.blockQueue;

import java.util.concurrent.ArrayBlockingQueue;

public class  BlockWaitNotify {
    public static void main(String[] args) {
        // 利用阻塞对了完成生产者和消费者

        // 创建阻塞队列对象
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        // ArrayBlockingQueue有界, 最多 capacity
        Cook c = new Cook(queue);
        Foodie f = new Foodie(queue);

        c.start();
        f.start();
    }
}
