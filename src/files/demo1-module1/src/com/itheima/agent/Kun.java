package com.itheima.agent;

public class Kun implements Star{
    private String name;

    public Kun() {
    }

    public Kun(String name) {
        this.name = name;
    }

    @Override
    public void sing() {
        System.out.println("鸡你太美");
    }

    @Override
    public void dance() {
        System.out.println("唱跳rap篮球");
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

    public String toString() {
        return "Kun{name = " + name + "}";
    }
}
