package com.itheima.DuoTaiDemo1;

public class Person {
    private int age;
    private String name;

    // 构造代码块
    {
        // 先于构造函数创立，构造函数会执行此代码块
        System.out.println("构造方法里的代码块");
    }
    // 静态代码块，随着类的加载而加载，在构造函数之前执行，只执行一次，不管几个实例
    static {
        System.out.println("静态代码块");
    }

    public Person(int age, String name) {
        System.out.println("构造函数");
        this.age = age;
        this.name = name;
    }

    public void feedPet(Animal a, String sth) {
        {
            // 变量代码块，执行完从内存里消失
            int aa = 10;
        }
        String s = "动物";
        if (a instanceof  Dog) {
            s = "狗";
        } else if (a instanceof  Cat) {
            s = "猫";
        }
        System.out.println(this.age + "岁的" + this.name + "养了一只" + a.getAge() + "岁" +  a.getColor() + "色的" + s);
        a.eat(sth);
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
