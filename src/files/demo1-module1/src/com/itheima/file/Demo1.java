package com.itheima.file;

import java.io.File;
import java.io.IOException;

public class Demo1 {
    public static void main(String[] args) throws IOException {

        // 1 创建文件对象,生成file对象
        String str1 = "E:\\keywords.txt";
        File f1 = new File(str1);
        System.out.println(f1);

        // 2 路径拼接, 可以是 file + string  或者 string + file
//        String p = "E:\\";
//        String c = "keywords.text";
//        System.out.println(new File(p, c));


        // 常用方法
//        System.out.println(f1.isDirectory());           // 是否文件夹
//        System.out.println(f1.isFile());                // 是否文件
//        System.out.println(f1.exists());                // 是否存在
//
//
//        System.out.println(f1.length());                // 返回文件大小（字节），long类型
//        // length() 无法获取文件夹大小，实际获取的是文件夹里面的文件大小
//        System.out.println(new File("E:\\pictures\\111").length() / 1024);     // 文件夹大小，必须是最里面的文件夹， Mb要除以1024， Gb再除以1024

//        相对路径（相对于当前项目）


//        System.out.println(f1.getAbsolutePath());           // 获取绝对路径
//        System.out.println(f1.getPath());                   // 返回定义文件时使用的路径（括号里参数是什么，返回的就是什么）
//
//        System.out.println(f1.getName());                   // 获取名字，带后缀，如果是文件夹，返回文件夹名字
//
//        System.out.println(f1.lastModified());              // 最后修改时间（毫秒数）



        // 创建、删除 文件/文件夹
//        File f2 = new File("E:\\a.txt");
//        boolean b = f2.createNewFile();                 // 只能创建文件， 返回boolean，创建新的空的文件，已存在则返回false，
//        System.out.println(b);
//
//        File f3 = new File("E:\\aa");
//        System.out.println(f3.mkdir());                                     // 创建文件夹,单层
//
//        File f4 = new File("E:\\bb\\cc\\dd");
//        System.out.println(f4.mkdirs());                                    // 多层或单层文件夹
//
//
//        System.out.println(f2.delete());                    // 删除文件，直接删，不走回收站
//        System.out.println(f3.delete());                    // 空文件夹，同上，如果文件夹里有内容，则失败
//        System.out.println(f4.delete());



        // 遍历文件夹
        File f5 = new File("E:\\pictures");
        File[] files = f5.listFiles();                      // 返回文件夹下所有内容（包含隐藏文件夹），并放到数组里返回，不存在的文件夹返回null
        for (File file : files) {
            System.out.println(file);
        }
    }
}
