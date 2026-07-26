package com.itheima.IO;

import java.io.*;

public class StringHuanchong {
    public static void main(String[] args) throws IOException {
        // 字符缓冲流，自带8192byte缓冲区域

        BufferedReader br = new BufferedReader(new FileReader("E:\\copy1.txt"));
//        String line = br.readLine();        // 一次读取整行，遇到回车换行结束
//        System.out.println(line);

        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\copy2.txt",true));

        String line;

        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();   // 空的一行
        }

        bw.close();
        br.close();
    }
}
