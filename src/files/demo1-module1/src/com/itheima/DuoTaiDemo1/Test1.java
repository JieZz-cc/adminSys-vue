package com.itheima.DuoTaiDemo1;

public class Test1 {
    public static void main(String[] args) {
        Dog d1 = new Dog();
        d1.setAge(2);
        d1.setColor("黑");

        Cat c1 = new Cat();
        c1.setAge(3);
        c1.setColor("黄");

        Person p1 = new Person(20, "老王");
        Person p2 = new Person(30, "小王");

        p1.feedPet(d1, "屎");
        p1.feedPet(c1, "老鼠");
    }
}
