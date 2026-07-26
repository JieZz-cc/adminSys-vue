package com.itheima.string;

import java.util.Arrays;

public class StrDemo1 {
    public static void main(String[] args) {
        String user = "jAVa";
        String mi = "abvdEF";
        System.out.println(user.charAt(2));         // 返回指定索引处的字符 V
        System.out.println(mi.substring(1, 3));     // 字符截取 bv
        System.out.println(mi.substring(1));     // 字符截取 从1 到尾

//        Scanner sc = new Scanner(System.in);
//        int count = 0;
//        while (count < 3) {
//            count++;
//            System.out.println("请输入用户名");
//            String userInput = sc.next();
//            System.out.println("请输入密码");
//            String miInput = sc.next();
//            if (user.equals(userInput) && mi.equals(miInput)) {
//                System.out.println("登录成功");
//                break;
//            } else {
//                System.out.println("请重新确认用户名和密码，当前还剩" + (3 - count) + "次机会");
//            }
//        }
//        if (count > 2) System.out.println("当前用户已锁定");

        int[] arr1 = {1, 2, 3};
        String ats1 = Arrays.toString(arr1);        // 数字 [1,2,3]

        String[] arr2 = {"one", "two", "three"};
        String ats2 = String.join(" ", arr2);       // 字符串 one two three

    }
}
