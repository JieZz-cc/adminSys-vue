package com.itheima.AbstractClass;

public abstract class  Person {
    // 父类中的抽象方法在子类中必须重写！！！
    // 抽象类不能new
    // 抽象类中不一定有抽象方法，但有抽象方法的一定是抽象类
    // 抽象类的子类必须重写抽象类中的所有抽象方法

    private String name;
    private int age;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void work();
    public abstract void work2();
}
