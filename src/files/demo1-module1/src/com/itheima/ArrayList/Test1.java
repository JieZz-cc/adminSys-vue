package com.itheima.ArrayList;

import java.util.ArrayList;

public class Test1 {
    public static void main(String[] args) {
        //  集合不能存储基本类型数据，需要对其进行包装类处理
        //  集合和数组的区别，集合长度可变，数组不可变
        // 集合可直接打印出来，打印的就是具体值

        //1 string类型集合
        ArrayList<String> list1 = new ArrayList<>();

        // 增加
        list1.add("11");                    // [11]
        list1.add("22");                    // [11, 22]
        list1.add("33");                    // [11, 22, 33]
        list1.add("44");                    // [11, 22, 33]
        list1.add("55");                    // [11, 22, 33]
        list1.add("66");                    // [11, 22, 33]
        list1.add("77");                    // [11, 22, 33]

        // 删除
        boolean res = list1.remove("22");     // 是否删除成功  true，删除不存的元素返回false

        String str1 = list1.remove(1);       // 根据索引删除，返回删除的元素，原有集合改变
        System.out.println(str1);                   // 33
        // 修改
        String str2 = list1.set(1, "aaa");          // 修改索引1 的值为aaa, 返回原来的值
        System.out.println(str2);                   // 44

        // 查询
        String str3 = list1.get(2);                 // 查询指定索引处的值 55
        System.out.println(str3);

        // 集合的长度
        System.out.println(list1.size());           // 长度  5

        list1.add(1, "211");          // 在索引1处增加一个元素
//        list1.clear();                              // 清空集合
//        list1.clone();
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }

//        list1.clear();
        System.out.println(list1);

    }
}
