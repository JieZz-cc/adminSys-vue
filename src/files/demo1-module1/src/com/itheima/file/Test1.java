package com.itheima.file;

import java.io.File;
import java.io.IOException;

public class Test1 {
    public static void main(String[] args) throws IOException {

        // 练习1  在当前项目下创建文件夹
//        File file = new File("javaCode\\aaa");
//        File file = new File("aaa");
//        file.mkdirs();
//
//        // 创建文件
//        File src = new File(file, "a.txt");
//        boolean b = src.createNewFile();
//        if (b) {
//            System.out.println("创建成功");
//        } else {
//            System.out.println("已存在");
//        }


        // 练习2
//        System.out.println(findJpg(new File("E:\\pictures\\111")));


        // 练习3
        System.out.println(countJpgs(new File("E:\\")));


        // 练习4 删除
//        delPack(new File("E:\\aa"));
    }

    public static boolean findJpg(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".jpg")) return true;
        }
        return false;
    }

    public static int countJpgs(File file) {
        int count = 0;
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    if (f.getName().endsWith(".jpg")) {
                        count++;
                    }
                } else {
                    count = count + countJpgs(f);
                }
            }
        }
        return count;
    }


    public static void delPack(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                f.delete();
            } else {
                delPack(f);
            }
        }
        file.delete();
    }
}
