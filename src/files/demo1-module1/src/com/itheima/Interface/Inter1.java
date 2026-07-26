package com.itheima.Interface;

public interface Inter1 {

    // 接口中可以有默认方法
    // 1、默认方法不是抽象方法，不需强制重写，如果重写，子类重写的时候要去掉default关键字
    // public 可以省略，default不能
    // 多个接口存在多个相同名称的默认方法，那么必须重写
    void method1();
    void method2();  // 不能有方法体




    // 默认方法
    default void show() {
        System.out.println("默认方法");
        t1();
    }

    // 静态方法   static void
    // 只能通过接口名调用
    static void bark() {
        System.out.println("bark");
        t2();
    }


    // 普通私有方法，给默认方法服务
    private void t1() {
        System.out.println("私有");
    }

    // 静态私有方法，给静态方法调用
    private static void t2() {
        System.out.println("静态私有");
    }
}
