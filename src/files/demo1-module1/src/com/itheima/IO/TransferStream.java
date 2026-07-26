package com.itheima.IO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class TransferStream {
    public static void main(String[] args) throws IOException {
        // 将gbk格式转换成utf-8


        FileReader fr = new FileReader("E:\\老师.txt", Charset.forName("UTF-8"));

        FileWriter FW = new FileWriter("demo1-module1\\tr1.txt", Charset.forName("GBK"));

        int b;
        while ((b = fr.read()) != -1) {
            FW.write(b);
        }

        FW.close();
        fr.close();

    }
}
