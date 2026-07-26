package com.itheima.test1;

public class Finally {
    public static void main(String[] args) {
        /*
            final 修饰方法： 表明该方法是最终方法，不能被修改；
            final 修饰类： 表明该类是最终类，不能被继承；
            final 修饰基本数据类型： 常量，只能赋值一次；
            final 修饰引用数据类型： 地址值不能改变，但内部的属性值可改变；

        */
    }
}
class Fu {
    public final void show() {
        System.out.println("ffff");
    }
}
class Zi extends Fu {
    // 父类的最终方法不能被重写
//    @Override
//    public void show() {
//        System.out.println("zzzzzzz");
//    }
}
