package com.itheima.Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BianLi {
    public static void main(String[] args) {
        Collection<String> col = new ArrayList<>();

        col.add("aaa");
        col.add("xxx");
        col.add("ccc");
        col.add("vvv");

        // 调用集合的迭代器方法，返回迭代器对象, 默认指向集合的0索引（arrayList有索引）
        Iterator<String> it = col.iterator();

        // it.hasNext()  当前索引是否有元素，返回boolean类型
        // it.next()  获取元素并把指针后移
        while (it.hasNext()) {
            // 循环中只能使用一次next方法
            // 迭代器循环时，不能操作集合
            String str = it.next();
            System.out.println(str);
        }



        // 增强 for 遍历： 所有的单列集合和数组才能使用，set不能用  col.for
        int[] arr1 = {1,2,3,4,5,6,7};
        for (String s: col) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (int i: arr1) {
            System.out.print(i + " ");
        }




        // lambda表达式遍历  （forEach）单列集合能用，数组用不了
        col.forEach(item -> {
            System.out.println(item);
        });



    }
}
