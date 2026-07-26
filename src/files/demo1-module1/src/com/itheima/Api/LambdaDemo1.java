package com.itheima.Api;

import java.util.Arrays;

public class LambdaDemo1 {
    public static void main(String[] args) {
        // Lambda表达式只能用来简化匿名内部类的书写
        // Lambda表达式只能简化函数式接口的匿名内部类的写法
        // 函数式接口： 有且只有一个抽象方法的接口叫函数式接口，接口上方可以加@FunctionInterface注解


        Integer[] arr5 = {1,45,7,2,3,9,6,4};
        // 降序 自己写规则
        Arrays.sort(arr5, (Integer o1, Integer o2) -> {       // 必须是包装类Integer
            return o2 - o1;         // 后面减去前面 降序，前面减去后面 升序
            });
        System.out.println(Arrays.toString(arr5));
    }
}
