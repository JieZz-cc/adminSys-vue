package com.itheima.FunctionImport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class  Demo1 {
    public static void main(String[] args) {

        // 1 引用本类中的静态方法
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "2","3","4","5","6");
        // 引用Integer类里的方法
        list1.stream().map(Integer::parseInt).forEach(s -> System.out.print(s));

        // 引用本类中的静态方法
//        list1.stream().map(Demo1::sToi).forEach(s -> System.out.print(s));






        // 2 引用全局方法
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "张无忌","赵敏","周芷若","谢逊","小昭");

//        list2.stream().filter(new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.startsWith("张") && s.length() > 2;
//            }
//        }).forEach(s -> System.out.println(s));

        // 引用其他类Func1中成员方法, func1不能是静态
        list2.stream().filter(new Func1()::func1).forEach(s -> System.out.println(s));
        // 引用本类中非静态的方法，public static void main 静态方法中没有this对象，只能实例化操作
        list2.stream().filter(new Demo1()::func1).forEach(s -> System.out.println(s));






        // 3 本类中的非静态方法
//        test1();





        // 4 引用构造方法
        ArrayList<String> list4 = new ArrayList<>();
        Collections.addAll(list4, "张无忌-15","周芷若-16","赵敏-14","小昭-17","谢逊-45","谢广坤-43");
        //  .map 封装对象
        System.out.println(list4.stream().map(Student::new).collect(Collectors.toList()));





        // 4
        ArrayList<String> list5 = new ArrayList<>();
        Collections.addAll(list5, "a","v","c");
        list5.stream().map(String::toUpperCase).forEach(s -> System.out.println(s));



        // 5 引用数组的构造方法





    }

    public static int sToi(String s) {
        return Integer.parseInt(s);
    }
    public boolean func1(String s) {
        return s.startsWith("张") && s.length() > 2;
    }

    public void test1() {
        ArrayList<String> list3 = new ArrayList<>();
        Collections.addAll(list3, "张无忌","赵敏","周芷若","谢逊","小昭");
        // test1非静态方法，有this
        list3.stream().filter(this::func1).forEach(s -> System.out.println(s));

        // 引用父类中的方法
//       list3.stream().filter(super::func1).forEach(s -> System.out.println(s));

    }
}
