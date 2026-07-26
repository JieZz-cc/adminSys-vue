package com.itheima.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class YasuoStream {
    public static void main(String[] args) throws IOException {

        // 压缩流
        File src1 = new File("E:\\copy1.txt");
        File dest1 = new File("E:\\");
//        zip(src1, dest1);               //压缩一个文件
        System.out.println(src1.getName());


        // 压缩文件夹
        File src3 = new File("E:\\pictures");
        System.out.println(src3.getName());
        File destParent = src3.getParentFile();     // pictures 的上级目录
        File dest3 = new File(destParent, src3.getName() + ".zip");     // 压缩包路径
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest3));
//        zip2(src3, zos, src3.getName());
        zos.close();




        // 解压
        File src2 = new File("E:\\test.zip");
        File dest2 = new File("E:\\");
//        unZip(src2, dest2);


    }

    public static void unZip(File src, File dest) throws IOException {
        // 创建一个解压缩流来读取压缩包中的数据
        ZipInputStream zip = new ZipInputStream(new FileInputStream(src));

        // 获取压缩包中的每一个zipentry对象，多层文件夹也会全部获取, 空的返回null
//        ZipEntry entry = zip.getNextEntry();
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            System.out.println(entry);
            if (entry.isDirectory()) {      // 文件夹
                File file = new File(dest, entry.toString());
                file.mkdirs();
            } else {
                FileOutputStream fos = new FileOutputStream(new File(dest, entry.toString()));
                int b;
                while ((b = zip.read()) != -1) {
                    fos.write(b);
                }
                fos.close();
                zip.closeEntry();
            }
        }
        zip.close();
    }

    public static void zip(File src, File dest) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(dest, "copy1.zip")));

        ZipEntry entry = new ZipEntry("copy1.txt");

        zos.putNextEntry(entry);        // 把entry放到压缩包中

        FileInputStream fis = new FileInputStream(src);
        int b;
        while ((b = fis.read()) != -1) {
            zos.write(b);
        }
        zos.closeEntry();
        zos.close();

    }

    public static void zip2(File src, ZipOutputStream zos, String name) throws IOException {
        File[] files = src.listFiles();

        for (File f : files) {
            if(f.isFile()) {
                ZipEntry entry = new ZipEntry(name + "\\" + f.getName());
                zos.putNextEntry(entry);
                FileInputStream fis = new FileInputStream(f);
                int b;
                while ((b = fis.read()) != -1) {
                    zos.write(b);
                }
                fis.close();
                zos.closeEntry();
            } else {
                zip2(f, zos, name + "\\" + f.getName());
            }
        }

    }
}
