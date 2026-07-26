package com.itheima.homework;

public class work2 {
    // 加密
    public static void main(String[] args) {
        Integer number = 1983;
        String s = number.toString();          // Integer 可以用tostring()
        String str = String.valueOf(number);   // int 数字转字符
        System.out.println(str.length());      // 字符串长度
        int[] arr = new int[str.length()];
        // 转成数组
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            arr[i] = Integer.parseInt(ch.toString());
        }
//        int[] arr = {1, 9, 8, 3};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] + 5;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % 10;
        }
        // 数字反转
        for (int i = 0, j = arr.length - 1; i < j;i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        // 数字拼接
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            num = num * 10 + arr[i];
        }
        System.out.println(num);
    }
}
