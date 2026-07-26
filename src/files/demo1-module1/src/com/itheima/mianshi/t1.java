package com.itheima.mianshi;

public class t1 {
    public static void main(String[] args) {
        // 超出[-128,127]范围的Integer实例不相等，而在范围内则会从常量池获取相同的引用
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);

        int i5 = 200;
        int i6 = 200;
        System.out.println(i5 == i6);
    }
}
