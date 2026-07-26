package com.itheima.test1;

import java.util.Scanner;

public class SwitchDemo {
    public static void main(String[] args) {
//        String noodles = "兰州11拉面";
//        switch (noodles) {
//            case "兰州拉面":
//                System.out.println("吃兰州拉面");
//                break;
//            case "海鲜面":
//                System.out.println("海鲜面");
//                break;
//            case "热干面":
//                System.out.println("热干面");
//                break;
//            case "炸酱面":
//                System.out.println("炸酱面");
//                break;
//            default:
//                System.out.println("suck my dick");
//                break;
//        }
        Scanner sc = new Scanner(System.in);
        int week = sc.nextInt();
        switch (week) {
            // 只有一行代码 大括号可以省略
            case 1,2,3,4,5 -> {
                System.out.println("工作日");
            }
            case 6,7 -> {
                System.out.println("休息日");
            }
            default -> System.out.println("fuck");
        }
    }
}
