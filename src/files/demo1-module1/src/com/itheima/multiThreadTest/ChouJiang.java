package com.itheima.multiThreadTest;

import java.util.ArrayList;
import java.util.Collections;

public class ChouJiang {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10,5,20,50,100,200,500,800,2,80,300,700);

        MyThread2 t1 = new MyThread2(list);
        MyThread2 t2 = new MyThread2(list);

        t1.setName("奖池1");
        t2.setName("奖池2");

        t1.start();
        t2.start();
    }
}
