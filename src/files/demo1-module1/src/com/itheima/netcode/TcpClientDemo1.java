package com.itheima.netcode;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClientDemo1 {
    public static void main(String[] args) throws IOException {
        // TCP协议，发送数据
        // 1、 创建socket对象
        Socket sk = new Socket("127.0.0.1",10001);

        // 2 从连接通道中获取输出流
        OutputStream os = sk.getOutputStream();

        //写出数据
        os.write("大帅逼".getBytes());

        os.close();
        sk.close();

    }
}
