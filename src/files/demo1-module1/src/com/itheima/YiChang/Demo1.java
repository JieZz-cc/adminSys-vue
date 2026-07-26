package com.itheima.YiChang;

import com.itheima.FunctionImport.Student;

public class Demo1 {
    public static void main(String[] args) {

        Student s1 = new Student("zhangsan", 22);

        Student[] arr = new Student[3];
//        System.out.println(arr[0].getName());


        /*
            System.out.println("112");
            System.out.println(2 / 0);      // 程序遇到异常时，停止运行
            System.out.println("333");
        */


        // 捕获异常
//        格式：try {} catch(异常类名 变量名) {}
        int[] arr2 = {1,2,3,4};
//        try {
//            // 里面放要执行的代码
//            // 依次执行，遇到异常了走catch
//            // 一个try可以有多个不同类型的catch
//            // 如果多个异常存在父子关系，父类一定写在子类下面
//
//            System.out.println(2/ 0);          // 遇到异常停止， 下面不执行
//
//            System.out.println(arr2[1]);
//            System.out.println(arr2[4]);        // 遇到异常走catch，下面不执行
//
//            System.out.println(2/0);
//
//        }catch(ArrayIndexOutOfBoundsException  | NullPointerException e) {      // 同时判断多个异常
//            // ArrayIndexOutOfBoundsException 数组超长
//            // 里面是处理异常的代码
//            System.out.println("数组超长");
//        }catch (ArithmeticException e) {
//            System.out.println("除数为0");
//        }
//        System.out.println("执行");       // try catch 后面的代码继续执行




        // 异常中的方法
        // 1、public string getMessage()    返回详细消息字符串
        // 2、public string toString()      返回简短描述
        // 1、public void printStackTrace()    错误信息输出在控制台

        try {
            System.out.println(arr[7]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
            System.out.println("最后一定会运行，除非虚拟机已终止");
        }


    }
}
