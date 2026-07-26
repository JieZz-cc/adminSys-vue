package com.itheima.tcptest2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10009);
        Socket sk = ss.accept();

        InputStreamReader irs = new InputStreamReader(sk.getInputStream());
        int b;
        while ((b = irs.read()) != -1) {
            System.out.print((char)b);
        }

        // 回复数据
        String str = "啊啊啊";
        OutputStream os = sk.getOutputStream();
        os.write(str.getBytes());

        sk.close();
        ss.close();
    }
}
