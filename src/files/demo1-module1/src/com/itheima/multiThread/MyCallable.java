package com.itheima.multiThread;

import java.util.concurrent.Callable;

// Callable 有泛型，默认object
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 20; i++) {
            sum += i;
        }
        return sum;
    }
}
