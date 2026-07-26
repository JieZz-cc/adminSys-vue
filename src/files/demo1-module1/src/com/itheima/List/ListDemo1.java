package com.itheima.List;

import java.util.*;

public class ListDemo1 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        // 添加元素
        list.add("aaa");
        list.add("xxx");
        list.add("ccc");
        list.add("vvv");


        list.add(1, "sss");     // 在索引1处增加sss


        // 删除
        System.out.println(list.remove(2));        // 删除指定索引处的元素，返回被删除的元素


        // 修改
        System.out.println(list.set(2, "rrr"));         // 修改指定索引处的元素，返回被修改的元素


        // 获取
        System.out.println(list.get(2));                // 返回指定索引处的元素



//        list.forEach(i -> System.out.println(i));

        // 列表迭代器
        // 获取一个列表迭代器的对象，里面的指针默认指向0索引，在遍历过程中可以添加元素
        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            String str = it.next();
            if("aaa".equals(str)) {
                it.add("ooo");
            }
            System.out.print(str + " ");
        }


        System.out.println(list);



        // 不可变集合 ，不可增删改,可遍历，可获取元素
        List<String> list2 = List.of("aa", "bb", "cc");
//        list2.add("dd");
        System.out.println(list2);
        // set 同上
        Set<String> set2 = Set.of("aa", "bb", "cc");
        System.out.println(set2);
        // map同上

        Map<String, String> map = Map.of("aa","vv", "cc","bb");  // map.of里面最多10个键值对


    }
}
