package com.itheima.netcode;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.ServerSocket;
        import java.net.Socket;

public class TcpServerDemo1 {
    public static void main(String[] args) throws IOException {
        // TCP协议，接受数据

        // 1 创建对象
        ServerSocket ss = new ServerSocket(10001);

        // 2 监听客户端的连接
        Socket sk = ss.accept();

        // 3 获取输入流
        InputStream is = sk.getInputStream();       // 字节流遇到中文会乱码，一个字节一个字节
        InputStreamReader ir = new InputStreamReader(is);   // 字节流转为字符流
        BufferedReader br = new BufferedReader(ir);         // 缓冲流提升效率

        int b;
        while ((b = br.read()) != -1) {
            System.out.print((char) b);
        }

        sk.close();     // 断开连接
        ss.close();     // 关闭服务器
    }
}