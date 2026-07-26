package com.itheima.JiCheng;

//   方法重写！！！！！！！！！！！！！
public class Test2 {
    public static void main(String[] args) {
        Zi z = new Zi();
//        z.show();
        z.action();
    }
}
class Fu {
    String name = "fu";
    private String age = "11";

    public void eat() {
        System.out.println("eat");
    }
    public void drink() {
        System.out.println("drink");
    }
}
class Zi extends Fu {
    String name = "zi";
    public void show() {
        String name = "zi1";
        System.out.println(name);       // zi1
        System.out.println(this.name);  // zi
        System.out.println(super.name); // fu    父类的变量
//        System.out.println(super.age); //     不能使用
    }
    public void drink() {
        System.out.println("zi drink");
    }
    public void action() {
        // 就近原则 同this.eat，本类中没有，向上父类中寻找 == super.eat()
        eat();          // 由于本类中没有，所以同 super.eat()

        // @Override
        // 方法重写，父类的方法不能满足子类的需求，可在子类中重写，方法名一样,
        // 子类覆盖子类当前虚方法表中的方法，不影响父类和其他继承自同一父类的的子类
        // 重写的方法尽量和父类中保持一致（名称、入参），！！！！！！
        // 静态方法和私有方法不能被重写，只有添加到虚方法表中的方法才能被重写（非private,非protected,非final）
        // 子类重写父类方法时，访问权限子类必须大于等于父类（空着不写 < protected < public）
        // 子类重写父类方法时, 返回值类型子类必须小于等于父类

        drink();        // 子类中有，直接调用，同this.drink()

        super.drink();  // 直接掉父类总的drink
    }
}
