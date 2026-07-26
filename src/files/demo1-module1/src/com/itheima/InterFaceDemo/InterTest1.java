package com.itheima.InterFaceDemo;

public class InterTest1 extends InterAdapter{
    // 适配器InterAdapter
    // 需要用到哪个方法，就重写哪个

    @Override
    public void method2() {
        System.out.println("重写1");
    }
}
