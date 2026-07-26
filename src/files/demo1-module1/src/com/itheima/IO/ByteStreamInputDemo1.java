package com.itheima.IO;

import java.io.FileInputStream;
import java.io.IOException;

public class ByteStreamInputDemo1 {
    public static void main(String[] args) throws IOException {
        // 字节输入流--------------------

        // 创建对象,读取文件
        FileInputStream fis1 = new FileInputStream("demo1-module1\\a.txt");

        // 读取数据
        // 一次读一个
        int r1 = fis1.read();       //读取字符在ascill表中所对应的数字
        System.out.println(r1);

        // 循环读取, fis1.read()没找到的话返回-1, read 读取一次，后移一位
        int b;
        while ((b = fis1.read()) != -1) {
            System.out.print((char)b);
        }


        // 释放资源
        fis1.close();

    }
}
