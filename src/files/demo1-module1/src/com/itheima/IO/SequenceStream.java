package com.itheima.IO;

import java.io.*;

public class SequenceStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 序列化流可以把对象写入本地文件

//        Student s1 = new Student("zhangs", 23);
//
//        // 创建序列化流输出对象
//        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("demo1-module1\\xulie1.txt"));
//
//        // 写出数据
//        os.writeObject(s1);
//
//        // 释放
//        os.close();




        // 反序列化流， 把序列化对象读取到程序中

        // 创建反序列化流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("demo1-module1\\xulie1.txt"));

        Object o1 = ois.readObject();
        System.out.println(o1);

        ois.close();




        // 读写多个对象时，可以将多个对象放在一个arraylist集合中，再序列化/反序列化集合

    }
}
