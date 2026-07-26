package com.itheima.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyDemo {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("demo1-module1\\a.txt");
        FileOutputStream fos = new FileOutputStream("demo1-module1\\b.txt");

        // 一个个字节拷贝
//        int b;
//        while ((b = fis.read()) != -1) {
//            fos.write(b);
//        }

        // 优化拷贝, 每次拷贝 1024 * 1024 * m 个字节
        int len;
        byte[] bytes = new byte[1024 * 1024 * 5];
        // 每次读取这么多字节
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }



        // 先开后关
        fos.close();
        fis.close();
    }
}
