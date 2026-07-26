package com.itheima.tcptest3;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class MyRunnable implements Runnable{
    Socket sk;
    public MyRunnable(Socket sk) {
        this.sk = sk;
    }

    @Override
    public void run() {
            try {
                BufferedInputStream bis = new BufferedInputStream(sk.getInputStream());
                // 生成随机字符串
                String uid = UUID.randomUUID().toString().replace("-","");
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\pictures\\111\\"+ uid +".jpg"));

                byte[] bytes = new byte[1024];
                int len;
                while ((len = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0 ,len);
                }

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sk.getOutputStream()));
                bw.write("上传成功");
                bw.newLine();
                bw.flush();     // 清空缓存
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                if (sk != null) {
                    try {
                        sk.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

    }
}
