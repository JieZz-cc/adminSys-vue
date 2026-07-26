package com.itheima.test1;

import java.util.Random;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] arr1 = {1, 2,3};
        String[] arr2 = {"11", "22", "33"};
        double[] arr3 = {1.78, 1.77, 1.56};
        arr1[0] = 0;
        // arr1.fori  自动生成循环语句
        int[] arr4 = new int[3];     // 初始化一个数组，长度为3， 默认值都是0
        String[] arr5 = new String[3];     // 初始化一个数组，长度为3， 默认值都是null

        int max = arr1[0];
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] > max) {
                max = arr1[i];
            }
        }
        System.out.println(max);


        int[] arr6 = new int[10];
        Random r = new Random();
        int sum = 0;
        for (int i = 0; i < arr6.length; i++) {
            int num = r.nextInt(100);
            arr6[i] = num;
            sum += arr6[i];
        }
        System.out.println(sum);
    }
}
