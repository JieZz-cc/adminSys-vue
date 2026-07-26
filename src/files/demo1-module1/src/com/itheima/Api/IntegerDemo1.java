package com.itheima.Api;

public class IntegerDemo1 {
    public static void main(String[] args) {
        // 自动装箱
        Integer i1 = 10;

        // 自动拆箱
        Integer i2 = new Integer(12);
        int i3 = i2;
        System.out.println(i3);

        // JDK5以后，int和integer可以看作同一个东西，因为再内部可以自动转化
        // 5 以后，不用new，直接赋值就行
        // Byte、Character、Integer、Short


        // 整数转换进制
        String str1 = Integer.toBinaryString(100);      // 2进制
        System.out.println(str1);

        System.out.println(Integer.toOctalString(100)); // 8进制

        System.out.println(Integer.toHexString(100)); // 16进制

        // 字符串转整数
        System.out.println(Integer.parseInt("123") + 1);
    }
}
