package com.itheima.YiChang;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GirlFriend gf = new GirlFriend();
        while (true) {
            try {
                System.out.println("女人姓名");
                String name = sc.nextLine();
                gf.setName(name);

                System.out.println("女人年龄");
                int age = Integer.parseInt(sc.nextLine());
                gf.setAge(age);

                break;
            }catch(NumberFormatException e) {
                System.err.println("年龄只能是数字");
            }catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(gf);
    }
}
