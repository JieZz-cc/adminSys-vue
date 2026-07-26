package com.itheima.string;

public class S1 {
    public static void main(String[] args) {
        char[] ch = {'a', 'b', 'v'};
        String s1 = new String(ch);
        System.out.println(s1);      // abv

        String s2 = new String("aa");
        String s3 = "aa";
        System.out.println(s2 == s3);       // false
        System.out.println(s2.equals(s3));  // 比较具体的数据值  true, 区分大小写
        System.out.println(s2.equalsIgnoreCase(s3));  // 比较具体的数据值  true, 不区分大小写
        // == 比较基本数据类型时（byte int short double float char boolean long  ），只比较值
        // 比较引用类型时，比的是地址值
    }
}
