package com.itheima.homework;

import java.util.Random;

// 生成随机验证码
public class work1 {
    public static void main(String[] args) {
        // 大小写字母 52位
        char[] chs = new char[52];
        for (int i = 0; i < chs.length; i++) {
            // ASCII表，小写97开始加， 大写A --- 65
            chs[i] = i < 26 ? (char)(97 + i) : (char)(65 + i -26);
        }
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = i;
        }
        String s = "";
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int randomIndex = r.nextInt(chs.length);
            s = s + chs[randomIndex];
        }
        System.out.println(s);

        int i = 1;
        i = i++;
        System.out.println(i);
    }
}
