package com.itheima.SuanFa;

import java.util.ArrayList;

public class BaseSearch {
    public static void main(String[] args) {
//        int[] arr1 = {1, 2, 3, 4,3,3, 5, 6, 7};
//        printList(search1(arr1, 3));

        // 二分查找,数组必须是排序后的
        int[] arr2 = {7, 23, 79, 81, 103, 127, 131, 147};
        System.out.println(binarySearch(arr2, 127));
        System.out.println(binarySearch(arr2, 81));
        System.out.println(binarySearch(arr2, 23));
        System.out.println(binarySearch(arr2, 111));
    }

    // 分块查找
//    public static int blockSearch(int[] arr, int number) {
//
//    }

    public static int binarySearch(int[] arr, int number) {
        int min = 0;    // 开始索引
        int max = arr.length - 1;

        while (true) {
            int mid = (min + max) / 2;
            if (min > max) return -1;       // 没找到
            if(arr[mid] > number) {
                // number在mid左边，max = mid -1
                max = mid - 1;
            } else if (arr[mid] < number) {
                // number在mid右边，min = mid + 1
                min = mid + 1;
            } else {
                return mid;
            }
        }
    }


    public static ArrayList<Integer> search1(int[] arr, int number) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) list.add(i);
        }
        return list;
    }

    public static void printList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
