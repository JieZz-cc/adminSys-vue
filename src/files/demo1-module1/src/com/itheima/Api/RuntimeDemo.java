package com.itheima.Api;

import java.io.IOException;

public class RuntimeDemo {
    public static void main(String[] args) throws IOException {

        Runtime r1 = Runtime.getRuntime();          // 当前系统的运行环境对象

//        r1.exit(0);                 // 同system.exit()

        System.out.println(r1.availableProcessors());       // 获取cpu线程数  ，本电脑12

        System.out.println(r1.maxMemory() / 1024 / 1024);           // jvm能从系统中获取的总内存大小，单位byte

        System.out.println(r1.totalMemory() / 1024 / 1024);           // jvm已经从系统中获取的内存大小，单位byte\

        System.out.println(r1.freeMemory() / 1024 / 1024);           // jvm剩余内存大小，单位byte


        // 运行cmd命令
        r1.exec("notepad");                     // 打开记事本

    }
}
