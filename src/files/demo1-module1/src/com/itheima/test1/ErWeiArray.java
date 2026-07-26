package com.itheima.test1;

public class ErWeiArray {
    // 二维数组
    public static void main(String[] args) {
        // 静态初始
        int[][] arr1 = new int[][]{{1,2,3}, {4,5,6,7}};
        int[][] arr2 = {
                {1,2,3},
                {4,5,6,7}
        };
//        System.out.println(arr2[0][0]);
        for (int i = 0; i < arr2.length; i++) {
            for (int i1 = 0; i1 < arr2[i].length; i1++) {
                System.out.print(arr2[i][i1]);
            }
            System.out.println();
        }

        // 动态初始
        // 二维数组长度为3， 每个一维数组长度为4
        int[][] arr3 = new int[3][4];
        
    }
}
