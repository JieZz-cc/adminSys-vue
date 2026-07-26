package com.itheima.MapDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HashMapDemo1 {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 20);
        Student s2 = new Student("lisi", 21);
        Student s3 = new Student("wangwu", 24);
        Student s4 = new Student("wangwu", 24);

        HashMap<Student, String> hmp = new HashMap<>();

        hmp.put(s1, "浙江");
        hmp.put(s2, "江苏");
        hmp.put(s3, "苏州");
        hmp.put(s4, "杭州");

        hmp.forEach((key, value) -> {
            System.out.println(key + " " + value);
        });


        // 练习2
        String[] arr = {"A", "B", "C", "D"};
        ArrayList<String> list = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 80; i++) {
            int index = r.nextInt(arr.length);
            list.add(arr[index]);
        }

        // 创建一个hashmap
        HashMap<String, Integer> mp = new HashMap<>();
        list.forEach(i -> {
            if (mp.containsKey(i)) {
                int count = mp.get(i);
                count++;
                mp.put(i, count);
            } else {
                mp.put(i, 1);
            }
        });

        int max = 0;
        mp.forEach((key, value) -> {
//            if (max < value) {
//                max = value;
//            }
        });

    }
}
