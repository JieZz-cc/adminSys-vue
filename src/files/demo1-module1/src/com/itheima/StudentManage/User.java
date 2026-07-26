package com.itheima.StudentManage;

import com.itheima.ToObject.Student;

public class User {
    private String name;
    private String passWord;
    private Student students;
    public User() {
    }

    public User(String name, String passWord, Student students) {
        this.name = name;
        this.passWord = passWord;
        this.students = students;
    }

    public Student getStudents() {
        return students;
    }

    public void setStudents(Student students) {
        this.students = students;
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
     * @return passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String toString() {
        return "User{name = " + name + ", passWord = " + passWord + "}";
    }
}
