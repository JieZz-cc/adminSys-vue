package com.itheima.NeiBuLei;

public class Test1 {
    public static void main(String[] args) {
        Car c = new Car();
        c.setName("bingli");

        c.show();

        // 实例化一个成员内部类  外部内名.内部内名  对象名 = new 外部类对象().new 内部类对象()
//        Car.Engine e1 = new Car().new Engine();
//        e1.show();

        // 成员内部类可以被修饰符修饰，实例化的两种方式
        // 1、权限够大，直接new 出来；
        // 2、权限小， 在外部类中通过一个方法return 出来，如private

        Object e2 = c.getEngine();   // 多态


        // 静态内部， 外部内名.内部内名  对象名 = new 外部类对象.new 内部类对象
        Car.Lun l1 = new Car.Lun();
        l1.show();
        // 静态方法, 直接调
        Car.Lun.show1();            // 11
    }
}
