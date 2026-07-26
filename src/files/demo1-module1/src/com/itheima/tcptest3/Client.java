package com.itheima.tcptest3;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sk = new Socket("127.0.0.1", 10008);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\pictures\\111\\a.jpg"));
        BufferedOutputStream bos = new BufferedOutputStream(sk.getOutputStream());
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0 ,len);
        }

        sk.shutdownOutput();

        BufferedReader br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
        System.out.println(br.readLine());

        sk.close();
    }
}
