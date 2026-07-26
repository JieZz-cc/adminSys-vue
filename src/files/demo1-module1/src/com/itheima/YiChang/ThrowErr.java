package com.itheima.YiChang;

import java.util.Arrays;

public class ThrowErr {
    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6};
        int[] arr2 = null;
        int[] arr3 = {};

        try {
            int max = fetchMax(arr3);
            System.out.println(max);
        } catch (ArrayIndexOutOfBoundsException |NullPointerException e) {
            System.err.println(e.getMessage());
        }

    }

    // 定义方法时可能会出现的异常，多个逗号隔开 throws ArrayIndexOutOfBoundsException, NullPointerException
    // ArrayIndexOutOfBoundsException, NullPointerException都是运行时异常，可以省略不写
    // 遇到编译异常时 ，throw必须要写
    public static int fetchMax(int[] arr) throws ArrayIndexOutOfBoundsException, NullPointerException {
        if(arr == null) {
            throw new NullPointerException();   // 停止运行后续代码
        }
        if (arr.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return Arrays.stream(arr).max().getAsInt();
    }
}
