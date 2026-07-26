package com.itheima.MapDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo1 {
    public static void main(String[] args) {


        // 创建hashmap对象
        Map<String, String> m1 = new HashMap<>();


        // 添加元素
        m1.put("111", "aaa");
        m1.put("222", "bbb");
        m1.put("333", "vvv");
        m1.put("444", "ccc");
        String str1 =  m1.put("444", "555");           // 已存在的键，值会被覆盖
        System.out.println(str1);                      // 返回444，把之前的值返回，如果是新增则返回null



        // 删除
        m1.remove("222");                         // 根据键删除，返回对应的值


        // 判断是否包含
        boolean res1 = m1.containsKey("111");           // 根据键判断
        boolean res2 = m1.containsValue("vvv");         // 根据值
//        System.out.println(res1);
//        System.out.println(res2);



        //判断是否为空
//        System.out.println(m1.isEmpty());


        // 长度
//        System.out.println(m1.size());


        //清空
//        m1.clear();


//        System.out.println(m1);

        // 遍历
        // 1通过键找值
        Set<String> keys = m1.keySet();         // 获取所有的键，放到单列集合中
        keys.forEach(item -> {
            String value = m1.get(item);
//            System.out.println(item + " = " + value);
        });

        //2
        Set<Map.Entry<String, String>> entries = m1.entrySet();    // 获取所有键值对象，放到一个set集合里
        entries.forEach(item -> {
            System.out.println(item.getKey() + "->" + item.getValue());
        });

        //3
        m1.forEach((key, value) -> {
            System.out.println(key + " " + value);
        });

    }
}
