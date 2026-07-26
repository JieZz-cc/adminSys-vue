package com.itheima.IO;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
    public static void main(String[] args) throws IOException {
        // 字符输出流， 文件写入

        // append 续写不清空
        FileWriter fw =  new FileWriter("E:\\a.txt", true); // 没有的话创建一个文件，有的话先清空

        fw.write("大帅逼");    // 直接写入字符串

        char[] chars = {'大', '帅', '比'};         // 字符数组形式写入
        fw.write(chars);

        String s = "啊啊啊啊啊";
        fw.write(s, 0, 3);    // 写入字符串一部分

        fw.write(chars, 0, 1);  // 写入字符数组的一部分

        fw.close();

    }
}
