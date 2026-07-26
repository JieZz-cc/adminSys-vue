package com.itheima.AbstractClass;

public class Student extends Person{

    public Student () {
        super();
    }
    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public void work() {
        System.out.println("work");
    }

    @Override
    public void work2() {
        System.out.println(11);
    }
}
