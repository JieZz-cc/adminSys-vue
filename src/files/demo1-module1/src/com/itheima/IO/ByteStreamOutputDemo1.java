package com.itheima.IO;

import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamOutputDemo1 {
    public static void main(String[] args) throws IOException {

        // 字节输出流-----------------


        // 输出流 创建对象，写入数据
        // 如果文件不存在，会创建一个新的；如果文件已经存在，则会清空文件；
        FileOutputStream fos1 = new FileOutputStream("demo1-module1\\a.txt", true);
        // 写入数据
        // 一次写一个字节数据
        fos1.write(97);         // 在txt文件中写入a，97对应ascii表中的字符a
        fos1.write(57);         // '9'
        fos1.write(55);         // '7'


        // 写一个字节组
        byte[] bytes = {97,98,99,100,101};
        fos1.write(bytes);


        // 写一个字节组的部分数据, [] 起始索引 长度
        fos1.write(bytes, 0, 3);


        // 换行-------------------
        String str1 = "dashuaibi";
        byte[] bytes1 = str1.getBytes();
        fos1.write(bytes1);

        // 换行的话要写一个换行符
        String str3 = "\r\n";
        byte[] bytes3 = str3.getBytes();
        fos1.write(bytes3);
        // 需要换行的内容
        String str2 = "666";
        byte[] bytes2 = str2.getBytes();
        fos1.write(bytes2);


        // 续写
        // new FileOutputStream("路径": '', 是否续写：false/true)
        // false: 每次new出来的对象会清空原来的内容；
        // true： 不会清空


        // 每次使用完流后都要释放资源
        fos1.close();




    }
}
