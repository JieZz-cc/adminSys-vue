package com.itheima.Api;

import java.util.Objects;

public class CloneDemo1 {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 对象浅克隆  引用类型数据变化，克隆对象里面的值也会跟着变化
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        User u1 = new User(1, "zhangsan", "1234qwer","girl7",arr1);


        /**
         * 步骤
         * 1、重写object中clone方法
         * 2、让javabean类实现cloneable接口
         * 3、创建对象并调用clone方法
         */
        User u2 =(User) u1.clone();

        int[] arr3 = u1.getData();
        u2.setId(2);
        arr3[0] = 100;


        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u2.toString());

        // 深克隆： 基本类型值拷贝，字符串复用， 引用数据类型会创建新的地址值并赋值

        // Objects的一些方法
        // 1、equals  底层调用user中的equals方法，如果重写了equals比较属性值，不重写比较地址值
        User u3 = new User();
        User u4 = null;
        System.out.println(Objects.equals(u3, u4));

        // isNull
        System.out.println(Objects.isNull(u3));     //  false
        System.out.println(Objects.isNull(u4));     //  true


    }
}
