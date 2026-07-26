package com.itheima.string;

import com.itheima.StaticLearn.ArrayUtil;

public class StringRotate {
    public static void main(String[] args) {
        String a = "abcde";
        String b = "eabcd";
        int count = 0;
        while (count < 5) {
            count++;
            a = rotate(a);
            if (a.equals(b)) {
                break;
            } else {
                continue;
            }
        }
        System.out.println(count);

        int[] arr1 = {1, 2, 3};
        System.out.println(ArrayUtil.printArr(arr1));
    }
    public static String rotate(String str) {
        char ch = str.charAt(0);
        str = str.substring(1) + ch;
        return str;
    }
}
