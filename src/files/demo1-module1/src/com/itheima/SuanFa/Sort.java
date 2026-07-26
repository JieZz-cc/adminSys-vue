package com.itheima.SuanFa;

import com.itheima.StaticLearn.ArrayUtil;

public class Sort {
    public static void main(String[] args) {

        int[] arr1 = {2,4,5,3,1};

        System.out.println(ArrayUtil.printArr(bubbleSort(arr1)));       // 冒泡

        System.out.println(ArrayUtil.printArr(selectSort(arr1)));       // 选择


        int[] arr2 = {3,44,38,5,47,15,36,26,27,2,46,49,50};
//        System.out.println(ArrayUtil.printArr(insertSort(arr2)));       // 插入排序

//        ArrayUtil.printArr(fastSort(arr2, 0, arr1.length - 1));
        fastSort(arr2, 0, arr1.length - 1);
        System.out.println(ArrayUtil.printArr(arr2));
    }

    public static int[] bubbleSort(int[] arr) {
        // 冒泡排序，循环length - 1 次， 左右逐一比较
        // 外循环表示循环几轮，内循环表示要做的事
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    // 选择排序, 从0开始，逐一比较后小的放左边
    public static int[] selectSort(int[] arr) {
        // 外循环表示循环几轮，内循环表示要做的事
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    // 插入排序
    public static int[] insertSort(int[] arr) {
        // 先找到无序的索引开始
        int startIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[i + 1]) {
                startIndex = i + 1;
                break;
            }
        }
        // 从startIndex索引开始，遍历到最后一个
        for (int i = startIndex; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j];
                arr[j] = arr[j -1];
                arr[j - 1] = temp;
                j--;
            }

        }
        return arr;
    }

    // 快速排序
    public static void fastSort(int[] arr, int i, int j) {
        int start = i;
        int end = j;

        if (start > end) return;

        int baseNumber = arr[i];      // 基准数

        while (start != end) {
            // baseNumber
            while (true) {
                if (end <= start || arr[end] < baseNumber) {
                    break;
                }
                end--;
            }
            // baseNumber
            while (true) {
                if (end <= start || arr[start] > baseNumber) {
                    break;
                }
                start++;
            }
            // 把start和end元素位置互换
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
        int temp = arr[i];
        arr[i] = arr[start];
        arr[start] = temp;

        // 确定左边范围，递归
        fastSort(arr, i, start - 1);
        // 确定右边范围， 递归
        fastSort(arr, start - 1, j);
//        return arr;
    }
}
