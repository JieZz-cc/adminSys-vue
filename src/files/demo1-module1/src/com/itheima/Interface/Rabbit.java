package com.itheima.Interface;

public class Rabbit extends Animal{
    public Rabbit() {
    }

    public Rabbit(String name, int age) {
        super(name, age);
    }

    @Override
    public void action() {
        System.out.println("rabbit is eating");
    }
}
