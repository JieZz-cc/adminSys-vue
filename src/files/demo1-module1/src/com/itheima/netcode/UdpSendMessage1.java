package com.itheima.netcode;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpSendMessage1 {
    public static void main(String[] args) throws IOException {

        DatagramSocket ds = new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("输入要发送的数据");
            String str = sc.nextLine();
            if ("886".equals(str)) {
                break;
            }
            byte[] bytes = str.getBytes();

            InetAddress address = InetAddress.getByName("127.0.0.1");

            int port = 10086;

            DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port);

            // 发送数据
            ds.send(dp);
        }
        // 释放资源
        ds.close();
    }
}
