package com.itheima.SuanFa;

public class Recursion {
    public static void main(String[] args) {
        // 递归求和
        System.out.println(getSum(10));

        System.out.println(multiplySum(5));
    }

    public static int getSum(int number) {
        if (number == 1) return 1;
        return number + getSum(number - 1);
        // 10 + 9 + 8+--- + 2 + 1
    }

    public static int multiplySum(int number) {
        if (number == 1) return 1;
        return number * multiplySum(number - 1);
    }
}
