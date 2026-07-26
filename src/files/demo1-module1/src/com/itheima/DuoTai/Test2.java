package com.itheima.DuoTai;

public class Test2 {
    public static void main(String[] args) {
        Animal a = new Dog();

        // 多态调用全局变量时候，编译看左边，运行也看左边
        // 编译看左边： javac编译时，先看左边父类有没有变量，有则编译成功，否则失败
        // 运行看左边： java运行代码时，实际获取的就是左边父类中的变量值
        System.out.println(a.name);   // 动物


        // 调用方法时 ，编译看左边，运行看右边
        // 编译看左边： javac编译时，先看左边父类有没有方法，有则编译成功，否则失败
        // 运行看右边： java运行代码时，实际运行的是子类中的方法
        a.bark();                   // 汪

    }
}

class Animal {
    String name = "动物";
    public void bark() {
        System.out.println("叫");
    }
}

class Dog extends Animal {
    String name = "狗";

    @Override
    public void bark() {
        System.out.println("汪");
    }
}

class Cat extends Animal {
    String name = "猫";

    @Override
    public void bark() {
        System.out.println("喵");
    }
}

