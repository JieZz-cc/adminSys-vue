package com.itheima.DuoTai;

public class Test1 {
//    无法从静态上下文中引用非静态 方法 ,静态方法只能调用静态方法
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("张三");
        s1.setAge(18);

        Teacher t1 = new Teacher();
        t1.setAge(30);
        t1.setName("少妇");

        show(s1);
        show(t1);

    }

    // 此方法既能接受老师，也可接受学生，  多态
    // 只能把入参写为学生和老师的父类
    // 多态的前提
    // 1有继承关系；2有方法的重写
    public static void show(Person p) {
        p.print();
    }
}
