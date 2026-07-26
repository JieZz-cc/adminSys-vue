package com.itheima.NeiBuLei;

public class Car {
    // 在A类中创建B类
    // 1、内部类可以访问外部类的全部，包括私有
    // 2、外部类必须新建内部类对象才能访问内部类

    private String name;
    private String color;
    int a = 10;
    static int b = 11;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void show() {
        System.out.println(this.name);

        Engine e = new Engine();
        System.out.println(e.engineName);
    }


    private class Engine {                  // 成员内部类（内中方法外），可以被修饰符修饰
        int engineAge = 10;
        String engineName = "五连发";

        int a = 20;

        public void show() {
            int a = 30;
            System.out.println(name);
            System.out.println(engineName);

            // 作用域
            System.out.println(a);              // 30
            System.out.println(this.a);         // 20
            System.out.println(Car.this.a);     // 10
        }
    }

    public Engine getEngine() {
        return new Engine();
    }

    // 静态内部类
    static class Lun {
        public void show() {
            // 静态只能访问静态 ！！
//            System.out.println(a);
            System.out.println(b);
        }

        // 静态方法
        public static void show1() {
            System.out.println(b);
        }
    }
}
