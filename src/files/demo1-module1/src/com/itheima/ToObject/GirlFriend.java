package com.itheima.ToObject;

public class GirlFriend {
    // javaBean类，没有main方法
    // private 私有，只能本类使用
    private String name;
    private int age;


    public void eat() {
        System.out.println("吃东西");
    }
    public void angry() {
        System.out.println("在生气");
    }
    // this 调用当前这个函数的地址， 当前指向全局
    public void setAge(int a) {
        if (a < 10) {
            System.out.println("非法年龄");
        } else {
            this.age = a;
        }
    }
    public int getAge() {
        return age;
    }
}
