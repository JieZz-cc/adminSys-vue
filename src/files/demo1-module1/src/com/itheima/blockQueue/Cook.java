package com.itheima.blockQueue;

import java.util.concurrent.ArrayBlockingQueue;

public class Cook extends Thread{
    ArrayBlockingQueue<String> queue;
    public Cook(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.put("蛋炒饭");
                System.out.println("厨师做了一碗蛋炒饭");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
