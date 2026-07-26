package com.itheima.ToObject;

public class GoodsTest {
    public static void main(String[] args) {
        Goods[] arr = new Goods[3];
        Goods g1 = new Goods("111", "斐济杯", 199.99, 100);
        Goods g2 = new Goods("222", "倒模", 299.99, 200);
        Goods g3 = new Goods("333", "河北采花", 399.99, 300);

        arr[0] = g1;
        arr[1] = g2;
        arr[2] = g3;
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getId());
        }
    }
}
