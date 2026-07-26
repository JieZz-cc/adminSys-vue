package com.itheima.test1;

public class MethodDemo {
    public static void main(String[] args) {
        playGame("游戏");
        System.out.println(methd1(1, 2));;
    }
//    方法
    public static void playGame(String str) {
        System.out.println("开始");
        System.out.println(str);
    }
    public static int methd1(int num1, int num2) {
//        void 可以不反悔值，int返回证书类型，string返回string， double返回double类型
        int res = num1 + num2;
        return res;
    }
}
