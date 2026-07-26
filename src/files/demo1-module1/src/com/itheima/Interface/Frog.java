package com.itheima.Interface;


public class Frog extends Animal implements Swim{
    // 继承一个类并实现了一个接口

    public Frog() {
    }

    public Frog(String name, int age) {
        super(name, age);
    }

    @Override
    public void action() {
        System.out.println("frog is eating");
    }
    @Override
    public void swim() {
        System.out.println("frog is swimming");
    }
}
