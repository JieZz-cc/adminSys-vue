package com.itheima.multiThreadTest;

public class QiangHongBao {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread1 t2 = new MyThread1();
        MyThread1 t3 = new MyThread1();
        MyThread1 t4 = new MyThread1();
        MyThread1 t5 = new MyThread1();

        t1.setName("张三");
        t2.setName("李四");
        t3.setName("王五");
        t4.setName("赵六");
        t5.setName("朱七");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
