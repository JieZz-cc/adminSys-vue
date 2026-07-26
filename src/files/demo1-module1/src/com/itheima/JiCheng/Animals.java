package com.itheima.JiCheng;

public class Animals {

    // 1 构造方法不能继承
    // 2 全局变量 公共跟私有都可以继承，但是公共可以使用，私有不能使用
    // 3 全局方法 公共可继承使用，私有不能继承
    public void eat() {
        System.out.println("吃东西");
    }

    public void drink() {
        System.out.println("喝水水");
    }
}
