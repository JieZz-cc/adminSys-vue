package com.itheima.StaticLearn;

public class ArrayUtil {
    // 工具类 ，用来做一些事情，不描述事物
    // 一般私有，不让外界创建他的对象
    private ArrayUtil() {

    }

    // 方法，需要定义为静态的，方便调用
    public static String printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]).append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // 方法，需要定义为静态的，方便调用
    public static double getAverage(double[] arr) {
        double sum = 0.0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum / arr.length;
    }
}
