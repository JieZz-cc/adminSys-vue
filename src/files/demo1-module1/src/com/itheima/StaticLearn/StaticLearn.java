package com.itheima.StaticLearn;

import com.itheima.ToObject.Student;

public class StaticLearn {
    public static void main(String[] args) {
        Student.teacher = "cc";

        Student s1 = new Student("001", "zhangsan", 12);
        Student s2 = new Student("002", "lisi", 14);

        s1.show();
        s2.show();



        // 静态方法通过    类名.方法   调用

    }
}
