package com.itheima.IO;

import java.io.*;

public class CharHuanChong {
    public static void main(String[] args) throws IOException {
        // 字节缓冲流拷贝，自带8192byte缓冲区域

        BufferedInputStream  bis= new BufferedInputStream(new FileInputStream("E:\\老师.txt"));

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\copy1.txt"));

        int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        // 一次读取多个， 字节只能用byte
        int len;
        byte[] bytes = new byte[1024];
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }

        bos.close();
        bis.close();

    }
}
