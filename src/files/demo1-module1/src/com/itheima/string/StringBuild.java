package com.itheima.string;

public class StringBuild {
    // 快速拼接字符串
    public static void main(String[] args) {
        // 创建容器对象
        StringBuilder sb = new StringBuilder("AA");     // AA
        StringBuilder sb2 = new StringBuilder();        // ''


        // 添加元素
        sb.append(1).append(true);    // AA1true


        // 反转
        sb.reverse();               // eurt1AA   stringbuilder对象

        System.out.println(sb.length());    // 长度 7

        // toString
        sb.toString();              // stringbuilder对象转为字符串 eurt1AA
        System.out.println(sb);
    }
}
