package com.itheima.DuoTaiDemo1;

public class Dog extends Animal{
    public Dog() {
    }

    public Dog(int age, String color) {
        super(age, color);
    }

    @Override
    public void eat(String sth) {
        System.out.println(getAge() + "岁"+ getColor() +"色狗在吃" + sth);
    }
    public void lookHome() {
        System.out.println("狗仔看家");
    }
}
