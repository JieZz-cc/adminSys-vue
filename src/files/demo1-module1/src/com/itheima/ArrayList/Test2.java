package com.itheima.ArrayList;

import com.itheima.ToObject.Student;

import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) {
        // 包装类
        // int -> Integer     char -> Character   其余几个基本类型就是把首字母大写 Byte  Float等

        // 整数类型
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        // 字符类型
        ArrayList<Character> list2 = new ArrayList<>();
        list2.add('1');

        int sum = 0;
        for (int i = 0; i < list1.size(); i++) {
            sum += list1.get(i);
        }
        System.out.println(sum);
        System.out.println(list2);

        ArrayList<Student> list3 = new ArrayList<>();
        Student s1 = new Student("001", "zhangsan", 18);
        Student s2 = new Student("002", "lisi", 18);
        list3.add(s1);
        list3.add(s2);
        System.out.println(list3);      // 自己写的类,没做处理,打印出来的都是地址值
    }
}
