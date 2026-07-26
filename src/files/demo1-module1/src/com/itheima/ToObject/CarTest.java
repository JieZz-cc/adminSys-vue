package com.itheima.ToObject;

import java.util.Scanner;

public class CarTest {
    public static void main(String[] args) {
        Car[] arr = new Car[3];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            Car c = new Car();

            System.out.println("请输入第" + (i + 1) + "个汽车品牌");
            String brand = sc.next();
            c.setBrand(brand);

            System.out.println("请输入第" + (i + 1) + "个汽车价格");
            int price = sc.nextInt();
            c.setPrice(price);

            System.out.println("请输入第" + (i + 1) + "个汽车类型");
            String type = sc.next();
            c.setType(type);

            arr[i] = c;
            System.out.println("已添加");
        }

        for (int i = 0; i < arr.length; i++) {
            Car cc = arr[i];
            System.out.println(cc.getBrand() + "," + cc.getPrice() + "," + cc.getType());
        }
    }
}
