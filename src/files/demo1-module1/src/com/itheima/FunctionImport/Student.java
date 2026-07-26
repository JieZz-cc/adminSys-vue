package com.itheima.FunctionImport;

public class Student {
    private String name;
    private int age;

    public Student() {
    }


    public Student(String s) {
        String[] arr = s.split("-");
        this.name = arr[0];
        this.age = Integer.parseInt(arr[1]);

    }

    public Student(String name, int age) {
        this.name = name;
        if(age <18 || age > 40) {
            // 抛出运行时异常,程序停止运行
            throw new RuntimeException();
        } else {
            this.age = age;
        }
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
