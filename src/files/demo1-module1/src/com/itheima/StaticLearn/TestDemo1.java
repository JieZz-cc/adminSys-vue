package com.itheima.StaticLearn;
// 测试工具类
public class TestDemo1 {
    public static void main(String[] args) {
        // 别的包中也可直接使用arrayutil工具类
        int[] arr1 = {1, 2,3,4,5};
        String str = ArrayUtil.printArr(arr1);
        System.out.println(str);

        double[] arr2 = {1.1, 2.2, 3.3, 4.4};
        double avg = ArrayUtil.getAverage(arr2);
        System.out.println(avg);
    }
}
