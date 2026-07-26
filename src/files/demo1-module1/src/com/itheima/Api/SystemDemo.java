package com.itheima.Api;

import com.itheima.StaticLearn.ArrayUtil;

import java.util.Arrays;

public class SystemDemo {
    public static void main(String[] args) {

        // 终止当前运行的虚拟机， 0 正常停止， 非0异常停止
//        System.exit(0);


        long l = System.currentTimeMillis();         //  返回long类型， 返回当前时间 毫秒数
        System.out.println(l);

        // arraycopy   数组拷贝，5个参数（源数据在，起始索引，目的地数组，起始索引，拷贝个数）
        // 拷贝的时候需要考虑数组长度，超出范围则报错
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr2 = new int[9];
        int[] arr3 = {1,2,2,2,2,2,2,2,2};
        System.arraycopy(arr1, 0, arr2, 0, 5);
        System.out.println(ArrayUtil.printArr(arr2));
        System.out.println(Arrays.toString(arr2));
        System.arraycopy(arr1, 0,arr3,2,5);
        System.out.println(ArrayUtil.printArr(arr3));
    }
}
