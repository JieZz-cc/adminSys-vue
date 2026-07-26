package com.itheima.SuanFa;

public class Test2 {
    public static void main(String[] args) {
        // 斐波那契数列
        // 从第三个数开始，每个数都是前两个数之和

        //方法1
        /*int[] arr1 = new int[12];
        arr1[0] = 1;
        arr1[1] = 1;
        for (int i = 2; i < arr1.length; i++) {
            arr1[i] = arr1[i - 1] + arr1[i - 2];
        }
        System.out.println(arr1[11]);*/

        // 方法2 递归求
        System.out.println(getNum(12));



        /*一堆桃子，猴子第一天吃了其中一半并且多吃了一个，
        以后每天猴子都吃当前剩下的一半，再多吃一个，
        第10天的时候（还没吃），只剩下一个桃子，问最初有多少个桃子
         day10   1
         day9   (day10 + 1) * 2 = 4
         day8   (day9 + 1) * 2 = 10
         day7   (day6 + 1) * 2 = 22
        */
        System.out.println(queryNum(1));





    }

    public static int getNum(int month) {
        if (month == 1 || month == 2) {
            return 1;
        }
        return getNum(month - 1) + getNum(month - 2);
    }

    public static int queryNum(int day) {
        if (day == 10) {
            return 1;
        }
        return (queryNum(day + 1) + 1) * 2;
    }


}
