package com.itheima.Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo1 {
    public static void main(String[] args) {

        // 先得到流水线，  list.stream(),把数据放上去，
        // stream流中间方法返回新的stream，原来的不能用，stream流建议链式使用，sout不返回新的流，当前流结束

        // 单列集合
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "a", "b", "c", "d", "e");

        Stream<String> stream1 = list1.stream();
        //  .filter， 把c开头的留下
        stream1.filter(s -> s.startsWith("c")).forEach(s -> System.out.println(s));


        // 双列集合 ， 无法直接创建stream流，要先转换成单列集合
        HashMap<String, Integer> hmp = new HashMap<>();
        hmp.put("q", 1);
        hmp.put("w", 2);
        hmp.put("e", 3);
        hmp.put("r", 4);

        // 方法一：获取stream流, keySet返回键的单列set集合
        hmp.keySet().stream().forEach(s -> System.out.println(s));      // 键

        // 方法二：entrySet返回键值对的单列set集合
        hmp.entrySet().stream().forEach(s -> System.out.println(s));    // 键值对


        // 数组  Arrays.stream(arr)获取stream流
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        String[] arr2 = {"j", "k", "l"};
        System.out.println(Arrays.stream(arr1).sum());
        Arrays.stream(arr2).filter(s -> "k".equals(s)).forEach(s -> System.out.println(s));



        // 离散数据stream,只能是引用数据类型，基本类型会被当成一整个元素
        Stream.of(arr2).filter(s -> "j".equals(s)).forEach(s -> System.out.println(s));



        // .limit(3)，获取前3个元素

        // .skip(3), 跳过前3个元素

        // .distinct()  元素去重

        // Stream.contact(stream1, stream2)   两个流合并成一个流


        // stream流中的终止方法 1、forEach；2、count（）；3、toArray(): 4、collect；
        System.out.println(Arrays.stream(arr1).count());            // 计数 6

        // Arrays.toString(arr)
        System.out.println(Arrays.toString(Arrays.stream(arr1).toArray()));


        // collect 收集流中的数据，放到集合中（list，set，map）
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "张无忌-男-15","周芷若-女-16","赵敏-女-14","小昭-女-17","谢逊-男-45","谢广坤-男-43");

        // 1.收集到list集合，  s.spilt("-")以-作为分隔符，分割成数组
        List<String> collectList = list2.stream()
                .filter(s -> "女".equals(s.split("-")[1]))
                .collect(Collectors.toList());
        System.out.println(collectList);    // list里面数据可重复

        // 2.set集合  数据不重复
        Set<String> collectSet = list2.stream().filter(s -> "女".equals(s.split("-")[1]))
                .collect(Collectors.toSet());
        System.out.println(collectSet);

        // map集合, 键名不能重复
//        Map<String, Integer> collectMap = list2.stream().filter(s -> "女".equals(s.split("-")[1]))
//                .collect(Collectors.toMap(
//                        new Function<String, String>() {
//                            @Override
//                            public String apply(String s) {
//                                return s.split("-")[0];
//                            }
//                        },
//                        new Function<String, Integer>() {
//                            @Override
//                            public Integer apply(String s) {
//                                return Integer.parseInt(s.split("-")[2]);
//                            }
//                        }
//                ));
        Map<String, Integer> collectMap = list2.stream().filter(s -> "女".equals(s.split("-")[1]))
                .collect(Collectors.toMap(
                        s -> s.split("-")[0],
                        s -> Integer.parseInt(s.split("-")[2])
                ));
        System.out.println(collectMap);
    }
}
