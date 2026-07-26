package com.itheima.Collection;

import java.util.ArrayList;
import java.util.Collection;

public class Demo1 {
    public static void main(String[] args) {
        /*Collection是一个接口，不能直接实例化，只能创建他实现类的对象：ArrayList*/

        // collection 分为两类：
//        1、list：单列；2、set：多列；

        Collection<String> coll1 = new ArrayList<>();
//        Collection<Integer> col2 = new Set();
        System.out.println(coll1.getClass());
        System.out.println(Collection.class);
        try {
            System.out.println(Class.forName("com.itheima.Collection.Demo1"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 添加元素
        // 1、往list集合里添加元素，返回值一直是true，因为list里面值可以重复；
        // 2、往set集合里添加元素，返回值true表示添加新元素成功，返回false表示元素已存在，添加失败
        System.out.println(coll1.add("aaa"));               // add 返回 boolean类型值

        coll1.add("vvv");
        coll1.add("ccx");


        // 删除，只能通过指定元素值删除，不能通过索引删除
        System.out.println(coll1.remove("aaa"));            // 方法返回boolean类型，true代表删除成功
        System.out.println(coll1.remove("xxd"));            // 方法返回boolean类型，删除不存在的元素返回false


        // 判断是否包含元素， 返回boolean
        System.out.println(coll1.contains("vvv"));


        // 判断是否为空
        System.out.println(coll1.isEmpty());                // 返回boolean


        // 集合长度
        System.out.println(coll1.size());                   // 返回int

        // 清空
        coll1.clear();                  // 方法返回空


        System.out.println(coll1);



    }
}
