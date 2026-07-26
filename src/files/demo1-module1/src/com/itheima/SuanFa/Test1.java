package com.itheima.SuanFa;

import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {

        GirlFriend gf1 = new GirlFriend("chenyao", 18, 1.65);
        GirlFriend gf2 = new GirlFriend("dilireba", 20, 1.68);
        GirlFriend gf3 = new GirlFriend("xuening", 18, 1.66);

//        ArrayList<GirlFriend> lis1 = new ArrayList<>();
        GirlFriend[] arr1 = {gf1, gf2, gf3};

        // 匿名内部类写法
//        Arrays.sort(arr1, new Comparator<GirlFriend>() {
//            // 先按年龄大小排序，年龄一样按身高排，身高一样按姓名首字母排
//            // 前面减去后面，升序排
//            @Override
//            public int compare(GirlFriend o1, GirlFriend o2) {
//                double temp = o1.getAge() - o2.getAge();    // 年龄排
//                temp = temp == 0 ? o1.getHeight() - o2.getHeight() : temp;
//                // o1.getName().compareTo(o2.getName()) 字符串比较大小，底层是转换ascll码表比较
//                temp = temp == 0 ? o1.getName().compareTo(o2.getName()) : temp;
//                if (temp > 0) {
//                    return 1;
//                } else if (temp < 0) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//            }
//        });

        // lambda表达式写法
        Arrays.sort(arr1, (o1, o2) -> {
                double temp = o1.getAge() - o2.getAge();    // 年龄排
                temp = temp == 0 ? o1.getHeight() - o2.getHeight() : temp;
                // o1.getName().compareTo(o2.getName()) 字符串比较大小，底层是转换ascll码表比较
                temp = temp == 0 ? o1.getName().compareTo(o2.getName()) : temp;
                if (temp > 0) {
                    return 1;
                } else if (temp < 0) {
                    return -1;
                } else {
                    return 0;
                }
        });
        System.out.println(Arrays.toString(arr1));
    }
}
