package com.itheima.tcptest2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sk = new Socket("127.0.0.1", 10009);

        String str = "草草草";
        OutputStream os = sk.getOutputStream();
        os.write(str.getBytes());

        sk.shutdownOutput();        // 结束标记

        // 接受服务器的数据
        InputStreamReader irs = new InputStreamReader(sk.getInputStream());
        int b;
        while ((b = irs.read()) != -1) {
            System.out.print((char)b);
        }

        sk.close();
    }
}
