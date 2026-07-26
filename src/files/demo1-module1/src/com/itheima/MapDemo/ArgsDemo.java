package com.itheima.MapDemo;

public class ArgsDemo {
    public static void main(String[] args) {
        // args  , 函数形参的个数可变化
        System.out.println(getSum(1, 2, 3));

    }

    // 底层，将参数转换成数组 args
    public static int getSum(int...args) {  // 只能有一个可变参数，如果有其他形参，可变参数必须在最后
        int sum = 0;
        for(int i : args) {
            sum += i;
        }
        return sum;
    }
}
