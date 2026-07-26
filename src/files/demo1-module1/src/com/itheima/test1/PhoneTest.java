package com.itheima.test1;

public class PhoneTest {
    public static void main(String[] args) {
        Phone p1 = new Phone();
        p1.brand = "iphone";
        p1.price = 5999.00;

        System.out.println(p1.brand);
        System.out.println(p1.price);

        p1.call();
        p1.playGame();
    }
}
