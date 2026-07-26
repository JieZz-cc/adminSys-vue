package com.itheima.Api;

public class RegxDemo1 {
    public static void main(String[] args) {
        // 正则表达式

        //1、
        String str1 = "";
        String regx = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";   // 8 -16位, 必须是数字加字母的组合
        System.out.println(str1.matches(regx));                         // false

        // 校验全数字，不能是0开头, 长度 6-20之间
        // [1-9] 开始，\\d表示此类型(当前数字类型)，{5, 19}表示次数
        String regx2 = "[1-9]\\d{5, 19}";

        /**   字符类匹配，只匹配一个字符
         * [abc]            只能是a,b,或c
         * [^abc]           除a,b,c之外任何字符
         * [a-zA-Z]         a-z, A-Z, 包括
         * [a-d[m-p]]       a-d,或m-p
         * [a-z&&[def]      a-z和def的交集：def
         * [a-z&&[^bc]      a-z和非bc的交集
         */

        /** 预定义字符（只匹配一个字符）
         *  .               任何字符
         *  \d              一个数字[0, 9)
         *  \D              非数字 [^0-9]
         *  \w              [a-zA-Z_0-9] 英文、数字、下划线
         *
         */

        /**
         * X?    X, 一次或0次
         * X*    x，零次或多次
         * x+    x，一次或多次
         * x{n}  x，正好n次
         * x{n,}  x，至少n次
         * x{n,m}  x，至少n次，不超过m次
         */
    }
}
