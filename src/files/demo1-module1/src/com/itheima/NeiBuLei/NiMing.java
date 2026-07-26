package com.itheima.NeiBuLei;

import com.itheima.AbstractClass.Person;
import com.itheima.Interface.Swim;

public class NiMing {
    public static void main(String[] args) {

        /**
         * new 类名或接口名() {
         *     重写方法（）；
         *  }
         */

        // 1重写接口
        new Swim() {
            @Override
            public void swim() {
                System.out.println("111");
            }
        };

        // 匿名内部类写法
        method(
                // 2 重写类名
                new Person() {
                    @Override
                    public void work() {
                        System.out.println("222");
                    }

                    @Override
                    public void work2() {

                    }
                }
        );
    }
    public static void method(Person p) {
        p.work();

    }
}
