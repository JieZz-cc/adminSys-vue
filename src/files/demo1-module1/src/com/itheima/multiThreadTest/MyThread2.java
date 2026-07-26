package com.itheima.multiThreadTest;

import java.util.ArrayList;
import java.util.Collections;

public class MyThread2 extends Thread{
    ArrayList<Integer> list;

//    ArrayList<Integer> list1 = new ArrayList<>();
//    ArrayList<Integer> list2 = new ArrayList<>();

    public MyThread2(ArrayList<Integer> list) {
        this.list = list;
    }
    @Override
    public void run() {
        ArrayList<Integer> list2 = new ArrayList<>();
        while (true) {
            synchronized (MyThread2.class) {
                if (list.size() == 0) {
                    System.out.println(getName() + ": " + list2);
                    break;
                } else {
                    Collections.shuffle(list);
                    int num = list.remove(0);
//                    System.out.println(getName() + "抽出了" + num + "元");
                   list2.add(num);
                }
            }
            // 休眠,尽可能均匀
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
