package com.itheima.tcptest1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 接收数据
        ServerSocket ss = new ServerSocket(10008);
        Socket accept = ss.accept();

        InputStreamReader irs = new InputStreamReader(accept.getInputStream());

        int b;
        while ((b = irs.read()) != -1) {
            System.out.print((char)b);
        }
        ss.close();
    }
}
