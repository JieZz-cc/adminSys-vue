package com.itheima.Interface;

public class Test2 implements Inter1, Inter2, Inter3{
//    接口必须全部重写里面的方法
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }

    @Override
    public void method4() {

    }

    // 直接接口名调用接口1里面的bark方法，
    public static void main(String[] args) {
        Inter1.bark();
    }
}
