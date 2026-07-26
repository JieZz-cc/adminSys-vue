package com.itheima.IO;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 编码
        String str1 = "啊啊啊";
        byte[] bytes1 = str1.getBytes();        // 获取编码数组, 默认utf-8编码，一个中文3个字节
        byte[] bytes2 = str1.getBytes("GBK");   // GBK编码
        System.out.println(Arrays.toString(bytes1));



        // 解码,  编解码不统一则乱码
        String str2 = new String(bytes1);
        String str3 = new String(bytes2, "gbk");
        System.out.println(str2);
        System.out.println(str3);

    }
}
