package com.itheima.string;

import java.util.Scanner;
import java.util.StringJoiner;

public class LuoMaShuZi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.next();
            boolean flag = validateStr(str);
            if(flag) {
                String outPut = transfer(str);
                System.out.println(outPut);
                break;
            } else {
                continue;
            }
        }
    }
    public static boolean validateStr(String str) {
        if (str.length() > 9) {
            System.out.println("字符串长度超过9，重新输入");
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0') {
                System.out.println("字符串只能为数字");
                return false;
            }
        }
        return true;
    }
    public static String transfer(String str) {
        String[] arr = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < str.length(); i++) {
            // 转数字减去48
            sj.add(arr[str.charAt(i) - 48]);
        }
        return sj.toString();
    }
}
