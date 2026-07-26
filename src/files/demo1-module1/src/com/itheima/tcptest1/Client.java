package com.itheima.tcptest1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sk = new Socket("127.0.0.1",10008);
        OutputStream os = sk.getOutputStream();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("输入要发送的数据");
            String str = sc.nextLine();
            if("88".equals(str)) {
                break;
            }
            os.write(str.getBytes());
        }
        sk.close();
    }
}
