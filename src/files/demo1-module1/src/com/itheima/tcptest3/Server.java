package com.itheima.tcptest3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,
                20,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        ServerSocket ss = new ServerSocket(10008);

        while (true) {      // 服务不停止，利用线程可多个用户上传
            Socket sk = ss.accept();
//            new Thread(new MyRunnable(sk)).start();
            pool.submit(new MyRunnable(sk));
        }



//        ss.close();
    }
}
