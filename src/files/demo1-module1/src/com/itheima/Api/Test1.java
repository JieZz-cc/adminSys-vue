package com.itheima.Api;

import java.util.ArrayList;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {

        getNum();


    }



    private static void getNum() {
        ArrayList<Integer> list1 = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入一个整数");
            String str = sc.nextLine();             // nextLine 返回字符串类型
            int num = Integer.parseInt(str);
            if (num < 1 || num > 100) {
                System.out.println("整数范围在0-100之间");
                continue;
            }
            list1.add(num);
            int sum = getSum(list1);
            if (sum > 200) {
                System.out.println(num);
                return;
            };
        }
    }

    private static int getSum(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }
}
