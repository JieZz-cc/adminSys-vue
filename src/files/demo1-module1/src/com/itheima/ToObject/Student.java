package com.itheima.ToObject;

public class Student {
    private String id;
    private String name;
    private int age;
    public static String teacher;          // 静态的数据，在一个对象中复制，后续new出来的多个对象共享

    // 静态方法中没有this
    // 静态方法中只能访问静态变量
    // 非静态方法可以访问所有

    // 如果没写构造方法，java虚拟机会默认给一个空参构造方法,
    // 如果有了，系统不在提供
    // 构造方法可重载，不带参数和带参数
    // 生成构造函数的快捷键  alt + insert

    // 插件PTG一键生成标准的javabean

    public Student () {
        System.out.println("空参");
    }
    // 带参数的默认构造函数
    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }


    // 右键空白，选择ptg to javabean

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void study() {
        System.out.println(this.name + "增在学习");
    }

    public void show() {
        System.out.println(this.name + " " + this.id + " " + this.age + " " + this.teacher);
    }
}
