package com.itheima.IO;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {
    public static void main(String[] args) throws IOException {
        // 字符输入流，读取
        FileReader F1 = new FileReader("E:\\老师.txt");

        // read()默认读取一个字节， 遇到中文一次读取多个，(GBK： 2， utf-8： 3)
        // 编码后转成10进制，用 char 进行强转


        // 一个一个读
//        int ch;
//        while ((ch = F1.read()) != -1) {
//            System.out.print((char) ch);
//        }


        // 多个读取
        char[] chars = new char[2];
        int len;
        while ((len = F1.read(chars)) != -1) {
            System.out.print(new String(chars, 0, len));
        }

        F1.close();
    }
}
