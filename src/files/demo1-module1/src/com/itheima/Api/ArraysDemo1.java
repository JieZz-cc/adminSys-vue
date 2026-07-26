package com.itheima.Api;

import java.util.Arrays;

public class ArraysDemo1 {
    public static void main(String[] args) {

        int[] arr1 = {1,2,3,4,5,6,7,8};
        int[] arre = {1,2,3};

        // 数组转字符串
        System.out.println(Arrays.toString(arr1));

        // 二分法查找元素
        System.out.println(Arrays.binarySearch(arr1, 10));       // 返回元素10在数组中的索引，没找到返回 负的插入点 -9

        // 拷贝数组， 新数组长度超过旧的话，空出的位置补0
        int[] arr2 = Arrays.copyOf(arr1, 4);                // 原来数组从0 开始，拷贝长度为4
        System.out.println(Arrays.toString(arr2));

        // 超过旧的长度补0
        int[] arr3 = Arrays.copyOfRange(arr1, 2, 9);        // 原数组指定索引范围内的拷贝, 前闭后开  [2, 9)，数组长度 to - from  超出补0
        System.out.println(Arrays.toString(arr3));

        // 填充数组  Arrays.fill 返回空
        Arrays.fill(arr1, 10);                                  // 将原数组所有元素的值都变为10，不改变长度，
        System.out.println(Arrays.toString(arr1));

        // 数组排序
        int[] arr4 = {1,45,7,2,3,9,6,4};
        Arrays.sort(arr4);                                          // 默认升序排
        System.out.println(Arrays.toString(arr4));

        Integer[] arr5 = {1,45,7,2,3,9,6,4};
        Arrays.sort(arr5, (a, b)-> {
            return b - a;
        });
        // 降序 自己写规则
//        Arrays.sort(arr5, new Comparator<Integer>() {       // 必须是包装类Integer
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 -o1;        // 后面 - 前面 降序，  前面 - 后面 升序
//            }
//        });
        System.out.println(Arrays.toString(arr5));
    }
}
