package com.itheima.IO;

import java.io.Serial;
import java.io.Serializable;


// Serializable接口里没有抽象方法，是标记型接口，代表当前这个类可以序列化

public class Student implements Serializable {

    // alt + enter生成序列号

    @Serial
    private static final long serialVersionUID = 4741351242972124020L;
    private String name;
    private int age;
    // transient此属性不会被序列化到本地文件
    private transient String address;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Student{name = " + name + ", age = " + age + "}";
    }
}
