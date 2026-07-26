package com.itheima.string;

import java.util.StringJoiner;

public class StringJoin {
    public static void main(String[] args) {
        // 创建容器，非空
        StringJoiner sj = new StringJoiner("---");
        // 以 [ 开始   ，间隔   ] 结束
        StringJoiner sj2 = new StringJoiner(",", "[","]");

        // 拼接 只支持字符串
        sj.add("aa").add("bb").add("ccc").add("d");     //  aa---bb---ccc---d
        sj2.add("aa").add("b");                         //  [aa,b]
        sj.length();                                    // 长度
        sj.toString();                                  // StringJoiner对象转字符串 aa---bb---ccc---d
        System.out.println(sj2);
    }
}
