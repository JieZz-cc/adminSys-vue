package com.itheima.test1;

public class ArrayDemo2 {
    // 方法抽取快捷键   ctrl + alt + m
    public static void main(String[] args) {
        int[] arr1 = {11, 22, 33, 44, 55};
//        printArr(arr1);
//        System.out.println(getMax(arr1));
        System.out.println(isPresent(arr1, 55));
        System.out.println(copyArrRange(arr1, 2, 4));
    }
    public static void printArr (int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            }  else {
                System.out.print(arr[i] + ",");
            }
        }
        System.out.print("]");
    }
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        return max;
    }
    public static boolean isPresent(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) return  true;
        }
        return false;
    }
    public static int[] copyArrRange(int[] arr, int start, int end) {
        int length = end - start;
        int[] newArr = new int[length];
        int index = 0;
        for (int i = start; i < end; i++) {
            newArr[index] = arr[1];
            index++;
        }
        return newArr;
    }
}
